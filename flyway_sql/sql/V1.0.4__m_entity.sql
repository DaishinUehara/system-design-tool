CREATE TABLE m_entity
(
 entity_no       integer      NOT NULL
,entity_cd       varchar(16)  NOT NULL
,entity_name     varchar(255) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(entity_no)
,CONSTRAINT m_entity_uk1 UNIQUE (entity_cd)
);

COMMENT ON TABLE m_entity IS 'エンティティマスタ';
COMMENT ON COLUMN m_entity.entity_no IS 'エンティティ番号';
COMMENT ON COLUMN m_entity.entity_cd IS 'エンティティコード';
COMMENT ON COLUMN m_entity.entity_name IS 'エンティティ名';
COMMENT ON COLUMN m_entity.create_datetime IS '登録日時';
COMMENT ON COLUMN m_entity.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_entity.update_datetime IS '更新日時';
COMMENT ON COLUMN m_entity.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_entity.del_flg IS '削除フラグ';

CREATE SEQUENCE m_entity_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_entity.entity_no;
