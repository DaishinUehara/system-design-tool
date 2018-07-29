CREATE TABLE m_message
(
 message_no      integer      NOT NULL
,message_cd      varchar(16)  NOT NULL
,message_text    varchar(256) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(message_no)
,CONSTRAINT m_message_uk1 UNIQUE (message_cd)
);

COMMENT ON TABLE m_message IS 'メッセージマスタ';
COMMENT ON COLUMN m_message.message_no IS 'メッセージ番号';
COMMENT ON COLUMN m_message.message_cd IS 'メッセージコード';
COMMENT ON COLUMN m_message.message_text IS 'メッセージテキスト';
COMMENT ON COLUMN m_message.create_datetime IS '登録日時';
COMMENT ON COLUMN m_message.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_message.update_datetime IS '更新日時';
COMMENT ON COLUMN m_message.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_message.del_flg IS '削除フラグ';

CREATE SEQUENCE m_message_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_message.message_no;
