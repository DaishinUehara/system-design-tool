CREATE TABLE m_service
(
 service_no       integer      NOT NULL
,service_cd       varchar(16)  NOT NULL
,service_name     varchar(255) NOT NULL
,create_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,create_user_no  integer      NOT NULL DEFAULT 0
,update_datetime timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP
,update_user_no  integer      NOT NULL DEFAULT 0
,del_flg         boolean      NOT NULL DEFAULT FALSE
,PRIMARY KEY(service_no)
,CONSTRAINT m_service_uk1 UNIQUE (service_cd)
);

COMMENT ON TABLE m_service IS 'サービスマスタ';
COMMENT ON COLUMN m_service.service_no IS 'サービス番号';
COMMENT ON COLUMN m_service.service_cd IS 'サービスコード';
COMMENT ON COLUMN m_service.service_name IS 'サービス名';
COMMENT ON COLUMN m_service.create_datetime IS '登録日時';
COMMENT ON COLUMN m_service.create_user_no IS '登録ユーザ番号';
COMMENT ON COLUMN m_service.update_datetime IS '更新日時';
COMMENT ON COLUMN m_service.update_user_no IS '更新ユーザ番号';
COMMENT ON COLUMN m_service.del_flg IS '削除フラグ';

CREATE SEQUENCE m_service_sq1 INCREMENT BY 1 START WITH 1 OWNED BY m_service.service_no;
