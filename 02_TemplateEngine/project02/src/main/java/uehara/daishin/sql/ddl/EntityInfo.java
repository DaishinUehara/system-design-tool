package uehara.daishin.sql.ddl;


import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="ENTITY_INFO")
@Getter
@Setter
@NamedQuery(name = "findAllEntityInfo", query="select c from EntityInfo c")
public class EntityInfo {

    @Id
    private BigDecimal tableId;

	/**
     * テーブル名
     */
    private String tablename;

    /**
     * テーブル(和名)
     */
    private String jptablename;

    @Override
    public String toString(){
    	return "{" + this.tableId + ", " + this.tablename + ", " + this.jptablename + "}";
    }
}
