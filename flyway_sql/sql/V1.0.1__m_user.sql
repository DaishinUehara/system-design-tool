CREATE TABLE m_user
(
 user_no       integer      NOT NULL
,user_cd       varchar(16)  NOT NULL
,user_name     varchar(255) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(user_no)
,CONSTRAINT m_user_uk1 UNIQUE (user_cd)
);

COMMENT ON TABLE m_user IS 'ユーザマスタ';
COMMENT ON COLUMN m_user.user_no IS 'ユーザ番号';
COMMENT ON COLUMN m_user.user_cd IS 'ユーザコード';
COMMENT ON COLUMN m_user.user_name IS 'ユーザ名';
COMMENT ON COLUMN m_user.create_datetime IS '登録日時';
COMMENT ON COLUMN m_user.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_user.update_datetime IS '更新日時';
COMMENT ON COLUMN m_user.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_user.del_flg IS '削除フラグ';

CREATE SEQUENCE m_user_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_user.user_no;
