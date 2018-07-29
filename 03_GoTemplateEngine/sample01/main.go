package main

import (
	"fmt"
	"log"
	"os"

	"./config"
	"./sdddl"
	"github.com/jmoiron/sqlx"
	_ "github.com/lib/pq" // コンパイル時に参照のみして直接パッケージを使わない場合に_アンスコ_ブランク+入手先とする
	// 設定ファイルの動的切り替え
)

/**
 * メイン関数
 * 最初に呼び出される処理。シグナルなどの処理を定義する。
 */
func main() {
	fmt.Println("Hello Go.")
	gomode := os.Getenv("GO_MODE")
	config.Init(gomode)
	conf := config.Get()
	var sslmode string
	if conf.DbconfigSslmode {
		sslmode = "enable"
	} else {
		sslmode = "disable"
	}
	db, err := sqlx.Open(conf.DbconfigDatabase, "user="+conf.DbconfigUser+"dbname="+conf.DbconfigDbname+"password="+conf.DbconfigPassword+"sslmode="+sslmode)
	// db, err := sqlx.Connect("postgres", "user=d-uehara dbname=systemdesigndb password=JfKdLs1003 sslmode=disable")
	checkError(err)
	defer db.Close()

	res1, err1 := db.Exec(sdddl.DropEntityInfo)
	if err != nil {
		fmt.Fprintf(os.Stderr, "DB Exec error! SQL=%s\n ERROR[%s]\n", sdddl.CreateEntityInfo, err1)
		os.Exit(1)
	} else {
		fmt.Fprintf(os.Stderr, "DB Exec Success! %s\n", res1)
	}

	res2, err2 := db.Exec(sdddl.CreateEntityInfo)
	if err != nil {
		fmt.Fprintf(os.Stderr, "DB Exec error! SQL=%s\n ERROR[%s]\n", sdddl.CreateEntityInfo, err2)
		os.Exit(1)
	} else {
		fmt.Fprintf(os.Stderr, "DB Exec Success! %s\n", res2)
	}

	tx := db.MustBegin()
	tx.Exec(sdddl.InsertEntityInfo, 1, "T00001", "テーブル00001")
	tx.Commit()

	os.Exit(0) // 終了コードを渡すにはos.Exitを用いる
}

func checkError(err error) {
	if err != nil {
		log.Fatal(err)
		fmt.Fprintf(os.Stderr, "file open error! %s\n", err)
		os.Exit(1) // 終了コード1で終了
	} else {
		os.Stdout.WriteString("SqlOpen Success!\n")
	}
}
