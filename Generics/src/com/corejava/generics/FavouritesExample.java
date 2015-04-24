package com.corejava.generics;

public class FavouritesExample {
    public static void main(String[] args) {
        Favourites favourites = new Favourites();
        favourites.add(String.class, "myString");
        favourites.add(Integer.class, 3);

        System.out.println(favourites.get(Integer.class)); //3
    }
}
