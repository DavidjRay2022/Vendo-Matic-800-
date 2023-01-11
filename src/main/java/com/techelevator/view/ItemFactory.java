package com.techelevator.view;

public abstract class ItemFactory {
    /*
    @Ron
    Added to address issues with extensibility. Now instead of having to manipulate the Machine
    class with any new item all changes can be implemented here. The createItem method is static since
    no object is created prior to use so it's called from a static context
     */
    public static Item createItem(String[] itemFromManifest){

        if(itemFromManifest[3].equals("Drink")){
           return new Drink(itemFromManifest[0],
                            itemFromManifest[1],
                            Double.parseDouble(itemFromManifest[2]),
                            itemFromManifest[3]);
        }
        else if(itemFromManifest[3].equals("Candy")){
            return new Candy(itemFromManifest[0],
                    itemFromManifest[1],
                    Double.parseDouble(itemFromManifest[2]),
                    itemFromManifest[3]);
        }
        else if(itemFromManifest[3].equals("Gum")){
            return new Gum(itemFromManifest[0],
                    itemFromManifest[1],
                    Double.parseDouble(itemFromManifest[2]),
                    itemFromManifest[3]);
        }
        else if(itemFromManifest[3].equals("Chip")){
            return new Chip(itemFromManifest[0],
                    itemFromManifest[1],
                    Double.parseDouble(itemFromManifest[2]),
                    itemFromManifest[3]);
        }
        else {
            System.out.println("Unable to determine class");
            return null;
        }
    }
}
