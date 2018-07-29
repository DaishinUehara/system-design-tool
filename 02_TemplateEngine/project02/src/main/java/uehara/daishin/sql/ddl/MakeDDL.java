package uehara.daishin.sql.ddl;



import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import lombok.val;

public class MakeDDL{
    public static void main(String args[]){
    	EntityManagerFactory emf = Persistence.createEntityManagerFactory("project02");
		EntityManager em = emf.createEntityManager();

            // Velocity 初期化
        try {
        	em.getTransaction().begin();
        	for( int i = 0; i < 10000; i++){
        		EntityInfo ei = new EntityInfo();
        		ei.setTableId(BigDecimal.valueOf(i));
        		ei.setTablename("T"+BigDecimal.valueOf(i));
        		ei.setJptablename("テーブル"+BigDecimal.valueOf(i));
        		em.persist(ei);
        	}
        	em.getTransaction().commit();

        	List<EntityInfo> entityInfoList=em.createQuery("select ei from EntityInfo ei",EntityInfo.class).getResultList();

        	// jarに固めるとvelocity.propertiesをうまく読み込めないため、コードで初期化する。
        	VelocityEngine ve = new VelocityEngine();
        	ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "CLASSPATH");
        	ve.setProperty("CLASSPATH.resource.loader.class", ClasspathResourceLoader.class.getName());
        	ve.init();
/*
        	VelocityContext context = new VelocityContext();
        	context.put("date", getMyTimestampFunction());
        	Template t = ve.getTemplate( "templates/email_html_new.vm" );
        	StringWriter writer = new StringWriter();
        	t.merge( context, writer );
*/

//        	Velocity.init();
            val vcontext = new VelocityContext();
            vcontext.put("entityInfoList",entityInfoList);

            val sw=new StringWriter();
            // テンプレートの作成
            Template template = null;
            // 以下のテンプレートはvelocityのテンプレートローダによって読み込まれる。
            // 設定はvelocity.propertiesにておこなう。
            template = ve.getTemplate("CreateTable.vm");

            // テンプレートとのマージ
            template.merge(vcontext,sw);
            System.out.println(sw.toString());
            sw.flush();

        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        } catch (ParseErrorException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
    		if (em.getTransaction().isActive()) {
    			em.getTransaction().rollback();
    		}
    		em.close();
        }
    }
}