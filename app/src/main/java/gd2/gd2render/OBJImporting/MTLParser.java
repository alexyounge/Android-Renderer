package gd2.gd2render.OBJImporting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import android.content.Context;

public class MTLParser {

	Context context;
	public  static Vector<Material> loadMTL(String file, Context context){
		BufferedReader reader=null;
		Vector<Material> materials=new Vector<Material>();
		String line;
		Material currentMtl=null;
		try { //try to open file
			reader = new BufferedReader(new InputStreamReader(context.getAssets().open("models/bear-obj.mtl")));
		} 		catch(IOException e){
		}
		if(reader!=null){
			try {//try to read lines of the file
				while((line = reader.readLine()) != null) {
					if(line.startsWith("newmtl")){
						System.out.println("got em");
						if(currentMtl!=null)
							materials.add(currentMtl);
						String mtName=line.split("[ ]+",2)[1];
						currentMtl=new Material(mtName);
					}
					else
					if(line.startsWith("	Ka")){
						String[] str=line.split("[ ]+");
						currentMtl.setAmbientColor(Float.parseFloat(str[1]), Float.parseFloat(str[2]), Float.parseFloat(str[3]));
						System.out.println("spag" + str[1]);
						System.out.println("spag" + str[2]);
						System.out.println("spag" + str[3]);
					}
					else
					if(line.startsWith("	Kd")){
						String[] str=line.split("[ ]+");
						currentMtl.setDiffuseColor(Float.parseFloat(str[1]), Float.parseFloat(str[2]), Float.parseFloat(str[3]));
					}
					else
					if(line.startsWith("	Ks")){
						String[] str=line.split("[ ]+");
						currentMtl.setSpecularColor(Float.parseFloat(str[1]), Float.parseFloat(str[2]), Float.parseFloat(str[3]));
					}
					else
					if(line.startsWith("	Tr") || line.startsWith("d")){
						String[] str=line.split("[ ]+");
						currentMtl.setAlpha(Float.parseFloat(str[1]));
					}
					else
					if(line.startsWith("	Ns")){
						String[] str=line.split("[ ]+");
						currentMtl.setShine(Float.parseFloat(str[1]));
					}
					else
					if(line.startsWith("	illum")){
						String[] str=line.split("[ ]+");
						currentMtl.setIllum(Integer.parseInt(str[1]));
					}
					else
					if(line.startsWith("	map_Ka")){
						String[] str=line.split("[ ]+");
						currentMtl.setTextureFile(str[1]);
					}
					else
					if(line.startsWith("	map_Kd")) {
						String[] str = line.split("[ ]+");
						currentMtl.setTextureFile(str[1]);
					}
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}
		return materials;
	}
}
