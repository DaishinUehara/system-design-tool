CREATE TABLE m_item
(
 item_no       integer      NOT NULL
,item_cd       varchar(16)  NOT NULL
,item_name     varchar(255) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(item_no)
,CONSTRAINT m_item_uk1 UNIQUE (item_cd)
);

COMMENT ON TABLE m_item IS '項目マスタ';
COMMENT ON COLUMN m_item.item_no IS '項目番号';
COMMENT ON COLUMN m_item.item_cd IS '項目コード';
COMMENT ON COLUMN m_item.item_name IS '項目名';
COMMENT ON COLUMN m_item.create_datetime IS '登録日時';
COMMENT ON COLUMN m_item.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_item.update_datetime IS '更新日時';
COMMENT ON COLUMN m_item.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_item.del_flg IS '削除フラグ';

CREATE SEQUENCE m_item_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_item.item_no;
