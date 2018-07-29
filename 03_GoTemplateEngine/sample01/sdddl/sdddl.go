package sdddl

// CreateEntityInfo entity_infoのCREATE TABLE文
var CreateEntityInfo = `
CREATE TABLE entity_info (
	table_id integer,
	table_name varchar(30) NOT NULL,
	jp_table_name varchar(30) NOT NULL,
	PRIMARY KEY (table_id),
	UNIQUE (table_name),
	UNIQUE (jp_table_name)
)`

// DropEntityInfo entity_infoのCREATE TABLE文
var DropEntityInfo = `DROP TABLE IF EXISTS entity_info`

// InsertEntityInfo entity_infoのINSERT文
var InsertEntityInfo = `
INSERT INTO entity_info (
	table_id,
	table_name,
	jp_table_name
) VALUES (
	$1,
	$2,
	$3
)`

// entityInfo entity_infoテーブルに対応する構造体
type entityInfo struct {
	tableID     int32  `db:"pk" column:"table_id"`
	tableName   string `db:"unique" column:"table_name"`
	jpTableName string `db:"unique" column:"jp_table_name"`
}
