CREATE TABLE m_screen
(
 screen_no      integer      NOT NULL
,screen_cd      varchar(16)  NOT NULL
,screen_name    varchar(256) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(screen_no)
,CONSTRAINT m_screen_uk1 UNIQUE (screen_cd)
);

COMMENT ON TABLE m_screen IS '画面マスタ';
COMMENT ON COLUMN m_screen.screen_no IS '画面番号';
COMMENT ON COLUMN m_screen.screen_cd IS '画面コード';
COMMENT ON COLUMN m_screen.screen_name IS '画面名';
COMMENT ON COLUMN m_screen.create_datetime IS '登録日時';
COMMENT ON COLUMN m_screen.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_screen.update_datetime IS '更新日時';
COMMENT ON COLUMN m_screen.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_screen.del_flg IS '削除フラグ';

CREATE SEQUENCE m_screen_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_screen.screen_no;
