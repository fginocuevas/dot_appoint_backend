package com.thenogicode.appoint.appuser.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("2")
public class DoctorAppUser extends AppUser{

}
