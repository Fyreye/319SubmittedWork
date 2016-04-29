package cs319;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

public class DataModel extends AbstractListModel
{
	private File data;
	private File csv;
	private List<String> list;
	
	public DataModel()
	{
		try
		{
			list = new ArrayList<String>();
			data = new File("companies.txt");
			csv = new File("companies.csv");
			list = Files.readAllLines(data.toPath());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public Object getElementAt(int index) 
	{
		return list.get(index);
	}

	@Override
	public int getSize() 
	{
		return list.size();
	}

	public void addItem(String item)
	{
		try
		{
			String itemD = "\r\n" + item;
			String itemC = "," + item;
			Files.write(Paths.get(data.getName()), itemD.getBytes(), StandardOpenOption.APPEND);
			list.add(item);
			Files.write(Paths.get(csv.getName()), itemC.getBytes(), StandardOpenOption.APPEND);
			fireContentsChanged(this, 0, list.size());
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void removeItem(int index)
	{
		try
		{
			String item = list.remove(index);
			File tempFile = new File("temp.txt");
			BufferedReader reader = new BufferedReader(new FileReader(data));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

			String currentLine;

			while((currentLine = reader.readLine()) != null)
			{
				String trimmed = currentLine.trim();
				trimmed.replace("\r\n", "");
				if(trimmed.equals(item)) continue;
				writer.write(currentLine + System.getProperty("line.separator"));
			}
			writer.close();
			reader.close();
			Files.delete(data.toPath());
			tempFile.renameTo(data);
			fireContentsChanged(this, 0, list.size());
			
			File tempC = new File("tempC.txt");
			BufferedReader csvReader = new BufferedReader(new FileReader(csv));
			BufferedWriter csvWriter = new BufferedWriter(new FileWriter(tempC));
			
			String cLine = csvReader.readLine();
			String[] entries = cLine.split(",");
			for(int i = 0; i < entries.length; i++)
			{
				String s = entries[i];
				if(s.equals(item)) continue;
				csvWriter.write(s);
				if(i != (entries.length-1))
					csvWriter.write(",");
			}
			csvWriter.close();
			csvReader.close();
			Files.delete(csv.toPath());
			tempC.renameTo(csv);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
	
}
