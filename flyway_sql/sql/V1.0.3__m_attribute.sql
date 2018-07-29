CREATE TABLE m_attr
(
 attr_no       integer      NOT NULL
,attr_cd       varchar(16)  NOT NULL
,attr_name     varchar(255) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(attr_no)
,CONSTRAINT m_attr_uk1 UNIQUE (attr_cd)
);

COMMENT ON TABLE m_attr IS '属性マスタ';
COMMENT ON COLUMN m_attr.attr_no IS '属性番号';
COMMENT ON COLUMN m_attr.attr_cd IS '属性コード';
COMMENT ON COLUMN m_attr.attr_name IS '項目名';
COMMENT ON COLUMN m_attr.create_datetime IS '登録日時';
COMMENT ON COLUMN m_attr.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_attr.update_datetime IS '更新日時';
COMMENT ON COLUMN m_attr.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_attr.del_flg IS '削除フラグ';

CREATE SEQUENCE m_attr_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_attr.attr_no;
