CREATE TABLE m_system
(
 system_no       integer      NOT NULL
,system_cd       varchar(16)  NOT NULL
,system_name     varchar(255) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(system_no)
,CONSTRAINT m_system_uk1 UNIQUE (system_cd)
);

COMMENT ON TABLE m_system IS 'システムマスタ';
COMMENT ON COLUMN m_system.system_no IS 'システム番号';
COMMENT ON COLUMN m_system.system_cd IS 'システムコード';
COMMENT ON COLUMN m_system.system_name IS 'システム名';
COMMENT ON COLUMN m_system.create_datetime IS '登録日時';
COMMENT ON COLUMN m_system.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_system.update_datetime IS '更新日時';
COMMENT ON COLUMN m_system.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_system.del_flg IS '削除フラグ';

CREATE SEQUENCE m_system_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_system.system_no;
