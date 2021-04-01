package com.thenogicode.appoint.appuser.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class SchedulerAppUser extends AppUser{

}
