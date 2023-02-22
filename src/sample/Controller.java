package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import animals.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    private TextField addAge;

    @FXML
    private Button addBth;

    @FXML
    private TextField addHeight;

    @FXML
    private TextField addName;

    @FXML
    private TextField addOwnerName;

    @FXML
    private TextField addWeight;

    @FXML
    private Button delBtn;

    @FXML
    private Button desBtn;

    @FXML
    private Button editBtn;

    @FXML
    private TextField edtAge;

    @FXML
    private TextField edtHeight;

    @FXML
    private TextField edtName;

    @FXML
    private TextField edtOwnerName;

    @FXML
    private TextField edtWeight;

    @FXML
    private TextArea listArea;

    @FXML
    private TextField pathToDes;

    @FXML
    private TextField pathToSer;

    @FXML
    private Button sBtn;

    @FXML
    private ComboBox<String> cmbBox;

    private AnimalList listOfAnimals;
    private AnimalDictionary animalDictionary;
    private SerializeWork serializator;

    @FXML
    void initialize() {
        animalDictionary = new AnimalDictionary();
        listOfAnimals = new AnimalList();
        serializator = new SerializeWork();
        for (String tempFigure : animalDictionary.dictionary.keySet()) {
            cmbBox.getItems().add(tempFigure);
        }
    }

    @FXML
    public void addBtnOnClickMethod() throws CloneNotSupportedException {
        Animal temp = animalDictionary.dictionary.get((String) cmbBox.getValue());
        Animal clonedEx = temp.clone();
        clonedEx.setName(addName.getText());
        clonedEx.setAge(Integer.parseInt(addAge.getText()));
        clonedEx.setOwnerName(addOwnerName.getText());
        clonedEx.physicalData.setHeight(Integer.parseInt(addHeight.getText()));
        clonedEx.physicalData.setWeight(Integer.parseInt(addWeight.getText()));
        listOfAnimals.add(clonedEx);
        outputList();
    }

    private void outputList() {
        listArea.clear();
        for (Animal temporaryAnimal: listOfAnimals.getList()) {
            listArea.appendText(temporaryAnimal.outFields());
        }
    }

    @FXML
    public void editBtnOnClickMethod() {
        Animal temp = null;
        for (Animal temporaryAnimal: listOfAnimals.getList()) {
            if (temporaryAnimal.getName().equals(edtName.getText())) {
                temp = temporaryAnimal;
                break;
            }
        }
        if (temp!=null) {
            temp.setAge(Integer.parseInt(edtAge.getText()));
            temp.setOwnerName(edtOwnerName.getText());
            temp.getPhysicalData().setHeight(Integer.parseInt(edtHeight.getText()));
            temp.getPhysicalData().setWeight(Integer.parseInt(edtWeight.getText()));
        }
        listOfAnimals.replace(temp);
        outputList();
    }

    public void delBtnOnClickMethod() {
        for (Animal temporaryAnimal : listOfAnimals.getList()) {
            if (temporaryAnimal.getName().equals(edtName.getText())) {
                listOfAnimals.remove(temporaryAnimal);
                break;
            }
        }
        outputList();
    }

    public void sBtnOnClickMethod() throws IllegalAccessException, IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(pathToSer.getText()));
        for (Animal tempAnimal : listOfAnimals.getList()) {
            String res = serializator.serial(tempAnimal, tempAnimal.getClass().getSimpleName(), 0);
            bw.write(res + "\n");
        }
        bw.flush();
    }

    public void desBtnOnClickMethod() throws IOException, IllegalAccessException, ClassNotFoundException, InstantiationException {
        BufferedReader br = new BufferedReader(new FileReader(pathToDes.getText()));
        StringBuilder list = new StringBuilder("");
        String line;
        String lineSeparator = System.getProperty("line.separator");
        while ((line = br.readLine()) != null) {
            list.append(line);
            list.append(lineSeparator);
        }
        listOfAnimals = serializator.deserialize(String.valueOf(list));
        outputList();
    }
}
