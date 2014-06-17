package solutions.willow.abp.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import solutions.willow.abp.model.GeographicalLocatorType;

@Entity
public class GeographicalLocator implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column
   private GeographicalLocatorType geographicalLocatorType;

   @Column(length = 45)
   private String line1;

   @Column(length = 45)
   private String line2;

   @Column(length = 45)
   private String city;

   @Column(length = 45)
   private String state;

   @Column(length = 45)
   private String country;

   @Column(length = 45)
   private String county;

   @Column(length = 45)
   private String zipcode;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object that)
   {
      if (this == that)
      {
         return true;
      }
      if (that == null)
      {
         return false;
      }
      if (getClass() != that.getClass())
      {
         return false;
      }
      if (id != null)
      {
         return id.equals(((GeographicalLocator) that).id);
      }
      return super.equals(that);
   }

   @Override
   public int hashCode()
   {
      if (id != null)
      {
         return id.hashCode();
      }
      return super.hashCode();
   }

   public GeographicalLocatorType getGeographicalLocatorType()
   {
      return this.geographicalLocatorType;
   }

   public void setGeographicalLocatorType(
         final GeographicalLocatorType geographicalLocatorType)
   {
      this.geographicalLocatorType = geographicalLocatorType;
   }

   public String getLine1()
   {
      return this.line1;
   }

   public void setLine1(final String line1)
   {
      this.line1 = line1;
   }

   public String getLine2()
   {
      return this.line2;
   }

   public void setLine2(final String line2)
   {
      this.line2 = line2;
   }

   public String getCity()
   {
      return this.city;
   }

   public void setCity(final String city)
   {
      this.city = city;
   }

   public String getState()
   {
      return this.state;
   }

   public void setState(final String state)
   {
      this.state = state;
   }

   public String getCountry()
   {
      return this.country;
   }

   public void setCountry(final String country)
   {
      this.country = country;
   }

   public String getCounty()
   {
      return this.county;
   }

   public void setCounty(final String county)
   {
      this.county = county;
   }

   public String getZipcode()
   {
      return this.zipcode;
   }

   public void setZipcode(final String zipcode)
   {
      this.zipcode = zipcode;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (line1 != null && !line1.trim().isEmpty())
         result += "line1: " + line1;
      if (line2 != null && !line2.trim().isEmpty())
         result += ", line2: " + line2;
      if (city != null && !city.trim().isEmpty())
         result += ", city: " + city;
      if (state != null && !state.trim().isEmpty())
         result += ", state: " + state;
      if (country != null && !country.trim().isEmpty())
         result += ", country: " + country;
      if (county != null && !county.trim().isEmpty())
         result += ", county: " + county;
      if (zipcode != null && !zipcode.trim().isEmpty())
         result += ", zipcode: " + zipcode;
      return result;
   }
}