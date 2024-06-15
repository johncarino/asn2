package com._assignements.assignment_2.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="objects" )
public class rectangle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uId;
    private int width, height;
    private String name, colour;

    public rectangle() {
    }

    public rectangle(String name, String colour, int width, int height) {
      this.name = name;
      this.colour = colour;
      this.width = width;
      this.height = height;
    }

    public String getColour(){return colour;}
    public void setColour(String colour){this.colour = colour;}
  
    public int getWidth(){return width;}
    public void setWidth(int width) {this.width = width;}
  
    public int getHeight() {return height;}
    public void setHeight(int height) {this.height = height;}
  
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
  
    public int getuId() {return uId;}
    public void setuId(int uId) {this.uId = uId;}
}
