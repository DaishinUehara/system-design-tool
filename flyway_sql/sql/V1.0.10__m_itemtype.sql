CREATE TABLE m_itemtype
(
 itemtype_no      integer      NOT NULL
,itemtype_cd      varchar(16)  NOT NULL
,itemtype_name    varchar(256) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(itemtype_no)
,CONSTRAINT m_itemtype_uk1 UNIQUE (itemtype_cd)
);

COMMENT ON TABLE m_itemtype IS '項目種別マスタ';
COMMENT ON COLUMN m_itemtype.itemtype_no IS '項目種別番号';
COMMENT ON COLUMN m_itemtype.itemtype_cd IS '項目種別コード';
COMMENT ON COLUMN m_itemtype.itemtype_name IS '項目種別名';
COMMENT ON COLUMN m_itemtype.create_datetime IS '登録日時';
COMMENT ON COLUMN m_itemtype.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_itemtype.update_datetime IS '更新日時';
COMMENT ON COLUMN m_itemtype.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_itemtype.del_flg IS '削除フラグ';

CREATE SEQUENCE m_itemtype_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_itemtype.itemtype_no;
