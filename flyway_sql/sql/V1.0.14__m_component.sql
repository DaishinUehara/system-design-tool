CREATE TABLE m_component
(
 component_no      integer      NOT NULL
,component_cd      varchar(16)  NOT NULL
,component_name    varchar(256) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(component_no)
,CONSTRAINT m_component_uk1 UNIQUE (component_cd)
);

COMMENT ON TABLE m_component IS 'コンポーネントマスタ';
COMMENT ON COLUMN m_component.component_no IS 'コンポーネント番号';
COMMENT ON COLUMN m_component.component_cd IS 'コンポーネントコード';
COMMENT ON COLUMN m_component.component_name IS 'コンポーネント名';
COMMENT ON COLUMN m_component.create_datetime IS '登録日時';
COMMENT ON COLUMN m_component.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_component.update_datetime IS '更新日時';
COMMENT ON COLUMN m_component.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_component.del_flg IS '削除フラグ';

CREATE SEQUENCE m_component_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_component.component_no;

