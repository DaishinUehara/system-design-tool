CREATE TABLE m_framework
(
 framework_no      integer      NOT NULL
,framework_cd      varchar(16)  NOT NULL
,framework_name    varchar(256) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(framework_no)
,CONSTRAINT m_framework_uk1 UNIQUE (framework_cd)
);

COMMENT ON TABLE m_framework IS 'フレームワークマスタ';
COMMENT ON COLUMN m_framework.framework_no IS 'フレームワーク番号';
COMMENT ON COLUMN m_framework.framework_cd IS 'フレームワークコード';
COMMENT ON COLUMN m_framework.framework_name IS 'フレームワーク名';
COMMENT ON COLUMN m_framework.create_datetime IS '登録日時';
COMMENT ON COLUMN m_framework.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_framework.update_datetime IS '更新日時';
COMMENT ON COLUMN m_framework.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_framework.del_flg IS '削除フラグ';

CREATE SEQUENCE m_framework_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_framework.framework_no;

ALTER TABLE m_framework ALTER COLUMN framework_no SET DEFAULT nextval('m_framework_sq1');
