set schema 'contabilizei';

-- Entities Tables
create table cliente (
  id BIGSERIAL PRIMARY KEY ,
  razao_social text NOT NULL,
  cnpj BIGINT NOT NULL,
  regime_tributario_id INTEGER NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL

);

CREATE INDEX cliente_cnpj_idx on cliente (cnpj);

create table anexo_cliente (
  cliente_id BIGSERIAL NOT NULL,
  anexo_id INTEGER NOT NULL,

  CONSTRAINT cliente_id_fk FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE INDEX anexo_cliente_idx on anexo_cliente (cliente_id);

create table nota_fiscal (
  id BIGSERIAL PRIMARY KEY,
  cliente_id BIGINT NOT NULL,
  numero BIGINT NOT NULL,
  data_emissao date NOT NULL,
  descricao text NOT NULL,
  valor_centavos BIGINT NOT NULL,
  anexo_id INTEGER NOT NULL,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL

  ,CONSTRAINT nota_fiscal_cliente_id_fk FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE INDEX nota_fiscal_cliente_idx on nota_fiscal (cliente_id);
CREATE INDEX nota_fiscal_data_emissao_idx on nota_fiscal (data_emissao);

create table imposto_mes (
  id BIGSERIAL PRIMARY KEY,
  cliente_id BIGINT NOT NULL,
  tipo_imposto_id INTEGER NOT NULL,
  vencimento date NOT NULL,
  valor_centavos BIGINT NOT NULL,
  mes_ano_referencia date NOT NULL,
  pago boolean NOT NULL default false,
  created_at timestamp NOT NULL,
  updated_at timestamp NOT NULL,

  CONSTRAINT nota_fiscal_cliente_id_fk FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE INDEX imposto_mes_cliente_tipo_imposto_idx on imposto_mes (cliente_id, tipo_imposto_id);
CREATE INDEX imposto_mes_cliente_vencimento_idx on imposto_mes (cliente_id, vencimento);
