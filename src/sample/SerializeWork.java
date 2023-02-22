package sample;

import animals.Animal;
import animals.AnimalDictionary;
import animals.AnimalList;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SerializeWork {

    private AnimalList listOfAnimals = new AnimalList();

    public String serial(Object obj, String name, int tabs) throws IllegalAccessException {
        String ser = "";
        ser+=makeTabs(tabs);
        ser += "<" + name + ">\n";
        for (Field field : obj.getClass().getFields()) {
            if(field.getType().isPrimitive() || field.getType().getSimpleName().equals("String")) {
                ser += makeTabs(tabs + 1);
                ser += "<" + field.getName() + ">\"" + field.get(obj).toString() + "\"</" + field.getName() + ">\n";
            } else {
                ser += serial(field.get(obj), field.getName(), tabs + 1);
            }
        }
        ser += makeTabs(tabs);
        ser+= "</" + name +">\n";
        return ser;
    }

    private String makeTabs(int count) {
        String tabs = "";
        for (int i =0; i<count; i++) {
            tabs +="\t";
        }
        return tabs;
    }

    public AnimalList deserialize(String elems) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        //getAllAnimals
        Pattern p1 = Pattern.compile("<[A-Z]\\w*>\\r\\n");
        Matcher m = p1.matcher(elems);
        int start;
        int end;
        while (m.find()) {
            start = m.start();
            end = m.end();
            String t = elems.substring(start+1, end - 3);
            Animal temp = Animal.createAnimal("animals." + t);
            listOfAnimals.add(temp);
        }
        //parseStringToElems
        Pattern p2 = Pattern.compile("</[A-Z]\\w*>\\r\\n");
        m = p2.matcher(elems);
        start = 0;
        String[] objects = new String[listOfAnimals.getSize()];
        for (int i = 0; i<objects.length; i++) {
            if (m.find()) {
                end = m.end();
                objects[i] = elems.substring(start, end - 2);
                start = end + 2;
            }
        }
        //getFields
        for (int i = 0; i<listOfAnimals.getSize(); i++) {
            Animal tempAnimal = listOfAnimals.getElem(i);
            deserializeField(tempAnimal, objects[i]);
            listOfAnimals.replace(tempAnimal);
        }
        return listOfAnimals;
    }

    private void deserializeField(Object obj, String fieldXML) throws IllegalAccessException {
        for (Field field : obj.getClass().getFields()) {
            Pattern p1 = Pattern.compile(field.getName() + ">\"[\\w.]*\"");
            Matcher m = p1.matcher(fieldXML);
            m.find();
            if (field.getType().isPrimitive() || field.getType().getSimpleName().equals("String")) {
                switch (field.getType().getSimpleName()) {
                    case "String":
                        field.set(obj, fieldXML.substring(m.start() + field.getName().length() + 2, m.end() - 1));
                        break;
                    case "int":
                        field.set(obj,Integer.parseInt(fieldXML.substring(m.start() + field.getName().length() + 2, m.end() - 1)));
                        break;
                    case "double":
                        field.set(obj, Double.parseDouble(fieldXML.substring(m.start() + field.getName().length() + 2, m.end() - 1)));
                        break;
                }
            } else {
                Pattern p2 = Pattern.compile("<" + field.getName() + ">[\\n\\t\\w\\W]*</" + field.getName() + ">");
                m = p2.matcher(fieldXML);
                m.find();
                String das = fieldXML.substring(m.start(), m.end());
                deserializeField(field.get(obj), das);
            }
        }
    }

}
