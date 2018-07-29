CREATE TABLE m_panel
(
 panel_no      integer      NOT NULL
,panel_cd      varchar(16)  NOT NULL
,panel_name    varchar(256) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(panel_no)
,CONSTRAINT m_panel_uk1 UNIQUE (panel_cd)
);

COMMENT ON TABLE m_panel IS 'パネルマスタ';
COMMENT ON COLUMN m_panel.panel_no IS 'パネル番号';
COMMENT ON COLUMN m_panel.panel_cd IS 'パネルコード';
COMMENT ON COLUMN m_panel.panel_name IS 'パネル名';
COMMENT ON COLUMN m_panel.create_datetime IS '登録日時';
COMMENT ON COLUMN m_panel.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_panel.update_datetime IS '更新日時';
COMMENT ON COLUMN m_panel.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_panel.del_flg IS '削除フラグ';

CREATE SEQUENCE m_panel_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_panel.panel_no;
