package com.javastart.io.file;

import com.javastart.exception.DataExportException;
import com.javastart.exception.DataImportException;
import com.javastart.model.Library;

import java.io.*;

public class SerializableFileManager implements FileManager {
    private static final String FILE_NAME = "src/main/resources/Library.io";

    @Override
    public void exportData(Library library) {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
        ) {
            oos.writeObject(library);
        } catch (FileNotFoundException e) {
            throw new DataExportException("No file " + FILE_NAME);
        }
        catch (IOException e) {
            e.printStackTrace();
            //            throw new DataExportException("Error writing data to file " + FILE_NAME);
        }
    }

    @Override
    public Library importData() {
        try (FileInputStream fis = new FileInputStream(FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis);
        ) {
            return (Library) ois.readObject();
        } catch (FileNotFoundException e) {
            throw new DataImportException("No file " + FILE_NAME);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataImportException("Error reading file " + FILE_NAME);
        } catch (ClassNotFoundException e) {
            throw new DataImportException("Incompatible data type in file " + FILE_NAME);
        }
    }
}
