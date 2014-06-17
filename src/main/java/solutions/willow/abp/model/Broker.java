package solutions.willow.abp.model;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;
import java.lang.Override;
import solutions.willow.abp.model.Gender;
import solutions.willow.abp.model.GeographicalLocator;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Broker implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id = null;
   @Version
   @Column(name = "version")
   private int version = 0;

   @Column(length = 30)
   private String firstName;

   @Column(length = 30)
   private String middleName;

   @Column(length = 30)
   private String lastName;

   @Column
   private Gender gender;

   @OneToMany(fetch = FetchType.LAZY)
   private Set<GeographicalLocator> geographicalAddresses = new HashSet<GeographicalLocator>();

   @Column(length = 9)
   private String ssn;

   @Temporal(TemporalType.DATE)
   private Date birthDate;

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
         return id.equals(((Broker) that).id);
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

   public String getFirstName()
   {
      return this.firstName;
   }

   public void setFirstName(final String firstName)
   {
      this.firstName = firstName;
   }

   public String getMiddleName()
   {
      return this.middleName;
   }

   public void setMiddleName(final String middleName)
   {
      this.middleName = middleName;
   }

   public String getLastName()
   {
      return this.lastName;
   }

   public void setLastName(final String lastName)
   {
      this.lastName = lastName;
   }

   public Gender getGender()
   {
      return this.gender;
   }

   public void setGender(final Gender gender)
   {
      this.gender = gender;
   }

   public Set<GeographicalLocator> getGeographicalAddresses()
   {
      return this.geographicalAddresses;
   }

   public void setGeographicalAddresses(
         final Set<GeographicalLocator> geographicalAddresses)
   {
      this.geographicalAddresses = geographicalAddresses;
   }

   public String getSsn()
   {
      return this.ssn;
   }

   public void setSsn(final String ssn)
   {
      this.ssn = ssn;
   }

   public Date getBirthDate()
   {
      return this.birthDate;
   }

   public void setBirthDate(final Date birthDate)
   {
      this.birthDate = birthDate;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (firstName != null && !firstName.trim().isEmpty())
         result += "firstName: " + firstName;
      if (middleName != null && !middleName.trim().isEmpty())
         result += ", middleName: " + middleName;
      if (lastName != null && !lastName.trim().isEmpty())
         result += ", lastName: " + lastName;
      if (ssn != null && !ssn.trim().isEmpty())
         result += ", ssn: " + ssn;
      return result;
   }
}