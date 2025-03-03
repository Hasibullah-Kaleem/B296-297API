package pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPojo implements Serializable {
	private String firstname;
	private String lastname;
	private String job;
	private String country;

	public UserPojo() {
	}

	public UserPojo(String firstname, String lastname, String job, String country) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.job = job;
		this.country = country;
	}

	public void setFirstname(String firstname){
		this.firstname = firstname;
	}

	public String getFirstname(){
		return firstname;
	}

	public void setLastname(String lastname){
		this.lastname = lastname;
	}

	public String getLastname(){
		return lastname;
	}

	public void setJob(String job){
		this.job = job;
	}

	public String getJob(){
		return job;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	@Override
 	public String toString(){
		return 
			"UserPojo{" + 
			"firstname = '" + firstname + '\'' + 
			",lastname = '" + lastname + '\'' + 
			",job = '" + job + '\'' + 
			",country = '" + country + '\'' + 
			"}";
		}
}