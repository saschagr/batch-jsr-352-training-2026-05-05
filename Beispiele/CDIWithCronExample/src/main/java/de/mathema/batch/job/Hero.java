package de.mathema.batch.job;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Hero {

  @GeneratedValue
  @Id
  private Long id;

  private String heroName;
  private String realName;
  private String address;
  private String country;
  private String powerLevel;
  private String team;
  private String affiliation;
  private String weapon;
  private String origin;

  public Hero() {
  }

  public Hero(String heroName, String realName, String address, String country, String powerLevel, String team,
    String affiliation, String weapon, String origin) {
    this.heroName = heroName;
    this.realName = realName;
    this.address = address;
    this.country = country;
    this.powerLevel = powerLevel;
    this.team = team;
    this.affiliation = affiliation;
    this.weapon = weapon;
    this.origin = origin;
  }
}
