create extension if not exists pgcrypto;

create type ruolo_utente as enum ('PROPRIETARIO', 'DIPENDENTE', 'CLIENTE');

-- Tutte le sedi Ongame, anche quelle che NON hanno aderito all'app
create table sedi (
  id            uuid primary key default gen_random_uuid(),
  codice        varchar(20)  not null unique,
  nome          varchar(150) not null,
  indirizzo     varchar(200),
  citta         varchar(100),
  cap           varchar(10),
  provincia     varchar(2),
  telefono      varchar(30),
  email         varchar(150),
  aderisce_app  boolean not null default false,
  data_adesione date,
  attiva        boolean not null default true,
  created_at    timestamptz not null default now(),
  updated_at    timestamptz not null default now()
);

-- Profilo applicativo di chi ha un login (id = auth.users.id di Supabase)
create table utenti (
  id         uuid primary key references auth.users(id) on delete cascade,
  nome       varchar(100) not null,
  cognome    varchar(100) not null,
  email      varchar(150) not null unique,
  ruolo      ruolo_utente not null default 'DIPENDENTE',
  attivo     boolean not null default true,
  created_at timestamptz not null default now(),
  updated_at timestamptz not null default now()
);

create table utenti_sedi (
  utente_id uuid references utenti(id) on delete cascade,
  sede_id   uuid references sedi(id)   on delete cascade,
  primary key (utente_id, sede_id)
);

-- Anagrafica clienti (account opzionale, per il futuro accesso cliente)
create table clienti (
  id                         uuid primary key default gen_random_uuid(),
  sede_id                    uuid not null references sedi(id),
  auth_user_id               uuid unique references auth.users(id),
  nome                       varchar(100) not null,
  cognome                    varchar(100) not null,
  data_nascita               date not null,
  email                      varchar(150),
  telefono_cifrato           text,
  telefono_hash              varchar(64),
  questionario_completato_il timestamptz,
  eliminato_il               timestamptz,
  created_at                 timestamptz not null default now(),
  updated_at                 timestamptz not null default now(),
  unique (sede_id, telefono_hash)
);
create index idx_clienti_compleanno on clienti
  ((extract(month from data_nascita)), (extract(day from data_nascita)))
  where eliminato_il is null;

create table consensi_cliente (
  id            uuid primary key default gen_random_uuid(),
  cliente_id    uuid not null references clienti(id) on delete cascade,
  tipo          varchar(30) not null,   -- PRIVACY | WHATSAPP_AUGURI | WHATSAPP_PROMO
  concesso      boolean not null,
  fonte         varchar(30) not null,   -- NEGOZIO | APP | WEB
  registrato_il timestamptz not null default now(),
  registrato_da uuid references utenti(id),
  unique (cliente_id, tipo)
);

create table categorie_preferenza (
  id     smallserial primary key,
  codice varchar(30) not null unique,
  nome   varchar(100) not null
);
create table preferenze (
  id           serial primary key,
  categoria_id smallint not null references categorie_preferenza(id),
  nome         varchar(150) not null,
  attiva       boolean not null default true,
  unique (categoria_id, nome)
);
create table clienti_preferenze (
  cliente_id    uuid references clienti(id) on delete cascade,
  preferenza_id int  references preferenze(id),
  created_at    timestamptz not null default now(),
  primary key (cliente_id, preferenza_id)
);

insert into categorie_preferenza (codice, nome) values
  ('FUNKO', 'Funko Pop'), ('FUMETTI', 'Fumetti'), ('ANIME', 'Anime'),
  ('MANGA', 'Manga'), ('VIDEOGIOCHI', 'Videogiochi');

-- sede_id null = offerta valida per tutta la catena
create table offerte (
  id            uuid primary key default gen_random_uuid(),
  sede_id       uuid references sedi(id),
  preferenza_id int references preferenze(id),
  titolo        varchar(150) not null,
  descrizione   text,
  valida_dal    date not null,
  valida_al     date not null,
  attiva        boolean not null default true,
  check (valida_al >= valida_dal)
);

create table messaggi_inviati (
  id                  uuid primary key default gen_random_uuid(),
  cliente_id          uuid not null references clienti(id),
  sede_id             uuid not null references sedi(id),
  tipo                varchar(30) not null default 'COMPLEANNO',
  template            varchar(100) not null,
  testo               text not null,
  stato               varchar(20) not null,   -- INVIATO | FALLITO
  provider_message_id varchar(100),
  errore              text,
  data_riferimento    date not null,
  inviato_il          timestamptz not null default now(),
  unique (cliente_id, tipo, data_riferimento)
);

create table audit_log (
  id        bigserial primary key,
  utente_id uuid,
  azione    varchar(50) not null,
  entita    varchar(50) not null,
  entita_id varchar(60),
  at        timestamptz not null default now()
);

-- RLS attiva senza policy: le tabelle sono raggiungibili solo dal backend
alter table sedi                 enable row level security;
alter table utenti               enable row level security;
alter table utenti_sedi          enable row level security;
alter table clienti              enable row level security;
alter table consensi_cliente     enable row level security;
alter table categorie_preferenza enable row level security;
alter table preferenze           enable row level security;
alter table clienti_preferenze   enable row level security;
alter table offerte              enable row level security;
alter table messaggi_inviati     enable row level security;
alter table audit_log            enable row level security;
