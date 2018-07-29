package config

import (
	"sync"

	"github.com/BurntSushi/toml"
)

// Config config/環境名.tomlで設定した設定値が入る構造体定義
type Config struct {
	// ここにTOMLと紐づけされる設定値を定義する。
	DbconfigDatabase string `toml:"dbconfig.database"`
	DbconfigUser     string `toml:"dbconfig.user"`
	DbconfigDbname   string `toml:"dbconfig.dbname"`
	DbconfigPassword string `toml:"dbconfig.password"`
	DbconfigSslmode  bool   `toml:"dbconfig.sslmode"`
}

var conf *Config
var once sync.Once

// Get config/環境名.tomlの設定を保持するインスタンス
func Get() *Config {
	return conf
}

// Init  config/環境名.tomlの設定値を取得しインスタンスに格納する
func Init(e string) {
	once.Do(func() {
		env := e
		if e == "" {
			env = "development"
		}
		conf = &Config{}
		toml.DecodeFile("config/"+env+".toml", conf)
	})
}
