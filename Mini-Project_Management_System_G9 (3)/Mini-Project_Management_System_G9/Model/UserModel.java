package Model;

// factory design pattern
// abstraction
// superclass of Student, Lecturer, and Admin
public abstract class UserModel{
    // Chang Siu Hong
    // this method is used to get String username in this class
    public abstract String getName();

    // Chang Siu Hong
    // this method is used to get String userpassword in this class
    public abstract String getPassword();

    // Chang Siu Hong
    // this method is used to get String userType in this class
    public abstract String getUserType();

    // Chang Siu Hong
    // this method is used to get String userfullname in this class
    public abstract String getUserFullName();

    // Chang Siu Hong
    // polymorphism
    // this method is used to print data to a list of String
    @Override
    public String toString() {
        return (getName() + "    " + getPassword() + "    " + getUserFullName() + "    " + getUserType());
    }
}