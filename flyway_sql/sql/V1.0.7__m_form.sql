CREATE TABLE m_form
(
 form_no      integer      NOT NULL
,form_cd      varchar(16)  NOT NULL
,form_name    varchar(256) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(form_no)
,CONSTRAINT m_form_uk1 UNIQUE (form_cd)
);

COMMENT ON TABLE m_form IS 'フォームマスタ';
COMMENT ON COLUMN m_form.form_no IS 'フォーム番号';
COMMENT ON COLUMN m_form.form_cd IS 'フォームコード';
COMMENT ON COLUMN m_form.form_name IS 'フォーム名';
COMMENT ON COLUMN m_form.create_datetime IS '登録日時';
COMMENT ON COLUMN m_form.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_form.update_datetime IS '更新日時';
COMMENT ON COLUMN m_form.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_form.del_flg IS '削除フラグ';

CREATE SEQUENCE m_form_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_form.form_no;
