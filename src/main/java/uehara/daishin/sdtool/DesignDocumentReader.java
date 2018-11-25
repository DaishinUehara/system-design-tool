package uehara.daishin.sdtool;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

import uehara.daishin.sdtool.apireader.ApiBookReader;
import uehara.daishin.sdtool.design.DesignDataBook;
import uehara.daishin.sdtool.design.DesignDataBooks;



/**
 * Hello world!
 *
 */
public class DesignDocumentReader
{
	public static void main( String[] args )
	{
		if (2 != args.length){
			System.err.println("ApiMaker ReadDirectory OutputDirectory");
			System.exit(1);
		}

		System.out.println( "[INFO]処理開始" );
		String arg0=args[0].replaceFirst("\\\\$", "");
		arg0=arg0.replaceFirst("\\/$", "");
		Path doc_dir = Paths.get(arg0);

		String arg1=args[1].replaceFirst("\\\\$", "");
		arg1=arg1.replaceFirst("\\/$", "");
		Path src_dir = Paths.get(arg1);
		if(!Files.exists(src_dir)){
			try {
				Files.createDirectories(src_dir);
			} catch (IOException e) {
				System.err.println( "[ERROR]ディレクトリ\""+src_dir+"\"の作成に失敗しました。" );
				e.printStackTrace();
				System.exit(1);
			}
		}

		DesignDataBooks designDataBooks=new DesignDataBooks();
		designDataBooks.setName("テスト設計書");
		designDataBooks.setDesignDataBookList(new ArrayList<DesignDataBook>());

		try {
			Files.walkFileTree(doc_dir, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					String excelFileName=file.toAbsolutePath().toString();
					if (excelFileName.endsWith(".xlsx")){
						System.out.println("[INFO]処理開始 ファイル名:\""+excelFileName+"\"");
						File f = new File(excelFileName);
						try{
							designDataBooks.getDesignDataBookList().add(
									ApiBookReader.ReadExcel(f,file.getFileName().toString().replaceFirst("\\.xlsx$", ""))
									);
						} catch (IOException e){
							System.err.println("[ERROR]処理失敗 ファイル名:\""+excelFileName+"\"");
							e.printStackTrace();
						}
						System.out.println("[INFO]処理終了 ファイル名:\""+excelFileName+"\"");
					}

					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			System.err.println("[ERROR]\""+doc_dir.toString()+"\"検索中にエラーが発生しました。");
			e.printStackTrace();
			System.exit(1);
		}

		System.out.println(designDataBooks);
		System.out.println("[INFO]処理終了");
		System.exit(0);
	}
}
