package com.etiya.renACar.business.constants.messages;

public class BusinessMessages {
    public class MaintenanceMessages{
        public static final String CAR_MAINTENANCE_ADDED_SUCCESSFULLY = "CAR_DAMAGE_LISTED_SUCCESSFULLY";
        public static final String CAR_MAINTENANCE_UPDATED_SUCCESSFULLY = "CAR_MAINTENANCE_UPDATED_SUCCESSFULLY";
        public static final String CAR_MAINTENANCE_DELETED_SUCCESSFULLY = "CAR_MAINTENANCE_DELETED_SUCCESSFULLY";
        public static final String CAR_MAINTENANCE_LISTED_SUCCESSFULLY = "CAR_MAINTENANCE_LISTED_SUCCESSFULLY";
        public static final String CAR_MAINTENANCES_LISTED_SUCCESSFULLY = "CAR_MAINTENANCES_LISTED_SUCCESSFULLY";
        public static final String CAR_MAINTENANCE_NOT_FOUND = "CAR_MAINTENANCE_NOT_FOUND";
        public static final String CAR_NOT_IN_MAINTENANCE = "CAR_NOT_IN_MAINTENANCE";
        public static final String CAR_MAINTENANCES_FOR_CAR_LISTED_SUCCESSFULLY = "CAR_MAINTENANCES_FOR_CAR_LISTED_SUCCESSFULLY";
        public static final String A_FUTURE_DATE_CANNOT_BE_ENTERED = "A_FUTURE_DATE_CANNOT_BE_ENTERED";
        public static final String CAR_MAINTENANCE_ALREADY_EXISTS_ = "CAR_MAINTENANCE_ALREADY_EXISTS";
    }

    public class CarMessages{
        public static final String CAR_ADDED_SUCCESSFULLY = "CAR_ADDED_SUCCESSFULLY";
        public static final String CAR_UPDATED_SUCCESSFULLY = "CAR_UPDATED_SUCCESSFULLY";
        public static final String BRAND_CAR_UPDATED_SUCCESSFULLY = "BRAND_CAR_UPDATED_SUCCESSFULLY";
        public static final String COLOR_CAR_UPDATED_SUCCESSFULLY = "COLOR_CAR_UPDATED_SUCCESSFULLY";
        public static final String KILOMETER_INFO_CAR_UPDATED_SUCCESSFULLY = "KILOMETER_INFO_CAR_UPDATED_SUCCESSFULLY";
        public static final String CAR_DELETED_SUCCESSFULLY = "CAR_DELETED_SUCCESSFULLY";
        public static final String CAR_LISTED_SUCCESSFULLY = "CAR_LISTED_SUCCESSFULLY";
        public static final String CARS_LISTED_SUCCESSFULLY = "CARS_LISTED_SUCCESSFULLY";
        public static final String CAR_LISTED_SUCCESSFULLY_BY_DAILY_PRICE = "CAR_LISTED_SUCCESSFULLY_BY_DAILY_PRICE";
        public static final String CAR_LISTED_AND_PAGINATED_SUCCESSFULLY = "CAR_LISTED_AND_PAGINATED_SUCCESSFULLY";
        public static final String CAR_SORTED_SUCCESSFULLY = "CAR_SORTED_SUCCESSFULLY";
        public static final String CAR_NOT_FOUND = "CAR_NOT_FOUND";
        public static final String CAR_ALREADY_IN_RENT = "CAR_ALREADY_IN_RENT";
        public static final String CAR_ALREADY_IN_MAINTENANCE = "CAR_ALREADY_IN_MAINTENANCE";
    }

    public class BrandMessages{
        public static final String BRAND_ADDED_SUCCESSFULLY = "BRAND_ADDED_SUCCESSFULLY";
        public static final String BRAND_UPDATED_SUCCESSFULLY = "BRAND_UPDATED_SUCCESSFULLY";
        public static final String BRAND_DELETED_SUCCESSFULLY = "BRAND_DELETED_SUCCESSFULLY";
        public static final String BRAND_LISTED_SUCCESSFULLY = "BRAND_LISTED_SUCCESSFULLY";
        public static final String BRANDS_LISTED_SUCCESSFULLY = "BRANDS_LISTED_SUCCESSFULLY";
        public static final String BRAND_NOT_FOUND = "BRAND_NOT_FOUND";
        public static final String BRAND_ALREADY_EXISTS = "BRAND_ALREADY_EXISTS";
    }

    public class ColorMessages{
        public static final String COLOR_ADDED_SUCCESSFULLY = "COLOR_ADDED_SUCCESSFULLY";
        public static final String COLOR_UPDATED_SUCCESSFULLY = "COLOR_UPDATED_SUCCESSFULLY";
        public static final String COLOR_DELETED_SUCCESSFULLY = "COLOR_DELETED_SUCCESSFULLY";
        public static final String COLOR_LISTED_SUCCESSFULLY = "COLOR_LISTED_SUCCESSFULLY";
        public static final String COLORS_LISTED_SUCCESSFULLY = "COLORS_LISTED_SUCCESSFULLY";
        public static final String COLOR_NOT_FOUND = "COLOR_NOT_FOUND";
        public static final String COLOR_ALREADY_EXISTS = "COLOR_ALREADY_EXISTS";
    }

    public class CarDamageMessages{
        public static final String CAR_DAMAGE_ADDED_SUCCESSFULLY = "CAR_DAMAGE_ADDED_SUCCESSFULLY";
        public static final String CAR_DAMAGE_UPDATED_SUCCESSFULLY = "CAR_DAMAGE_UPDATED_SUCCESSFULLY";
        public static final String CAR_DAMAGE_DELETED_SUCCESSFULLY = "CAR_DAMAGE_DELETED_SUCCESSFULLY";
        public static final String CAR_DAMAGE_LISTED_SUCCESSFULLY = "CAR_DAMAGE_LISTED_SUCCESSFULLY";
    }

    public class CorporateCustomerMessages{
        public static final String CORPORATE_CUSTOMER_ADDED_SUCCESSFULLY = "CORPORATE_CUSTOMER_ADDED_SUCCESSFULLY";
        public static final String CORPORATE_CUSTOMER_UPDATED_SUCCESSFULLY = "CORPORATE_CUSTOMER_UPDATED_SUCCESSFULLY";
        public static final String CORPORATE_CUSTOMER_DELETED_SUCCESSFULLY = "CORPORATE_CUSTOMER_DELETED_SUCCESSFULLY";
        public static final String CORPORATE_CUSTOMER_NOT_FOUND = "CORPORATE_CUSTOMER_NOT_FOUND";
    }

    public class IndividualCustomerMessages{
        public static final String INDIVIDUAL_CUSTOMER_ADDED_SUCCESSFULLY = "INDIVIDUAL_CUSTOMER_ADDED_SUCCESSFULLY";
        public static final String INDIVIDUAL_CUSTOMER_UPDATED_SUCCESSFULLY = "INDIVIDUAL_CUSTOMER_UPDATED_SUCCESSFULLY";
        public static final String INDIVIDUAL_CUSTOMER_DELETED_SUCCESSFULLY = "INDIVIDUAL_CUSTOMER_DELETED_SUCCESSFULLY";
        public static final String INDIVIDUAL_CUSTOMER_NOT_FOUND = "INDIVIDUAL_CUSTOMER_NOT_FOUND";
        public static final String NATIONAL_IDENTITY_NOT_VALID = "NATIONAL_IDENTITY_NOT_VALID";
    }

    public class CustomerMassages{
        public static final String CUSTOMERS_LISTED_SUCCESSFULLY = "CUSTOMERS_LISTED_SUCCESSFULLY";
    }

    public class UserMessages{
        public static final String USERS_LISTED_SUCCESSFULLY = "USERS_LISTED_SUCCESSFULLY";
        public static final String USER_UPDATED_SUCCESSFULLY = "USER_UPDATED_SUCCESSFULLY";
        public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
        public static final String EMAIL_ALREADY_EXISTS = "EMAIL_ALREADY_EXISTS";
    }

    public class RentMessages{
        public static final String RENT_ADDED_SUCCESSFULLY = "RENT_ADDED_SUCCESSFULLY";
        public static final String RENT_UPDATED_SUCCESSFULLY = "RENT_UPDATED_SUCCESSFULLY";
        public static final String ENDED_KILOMETER_INFO_UPDATED_SUCCESSFULLY = "ENDED_KILOMETER_INFO_UPDATED_SUCCESSFULLY";
        public static final String RENT_DELETED_SUCCESSFULLY = "RENT_DELETED_SUCCESSFULLY";
        public static final String RENT_LISTED_SUCCESSFULLY = "RENT_LISTED_SUCCESSFULLY";
        public static final String RENTS_LISTED_SUCCESSFULLY = "RENTS_LISTED_SUCCESSFULLY";
        public static final String RENTS_FOR_CAR_LISTED_SUCCESSFULLY = "RENTS_FOR_CAR_LISTED_SUCCESSFULLY";
        public static final String CAR_NOT_IN_RENT = "CAR_NOT_IN_RENT";
        public static final String CITIES_ARE_SAME = "CITIES_ARE_SAME";
        public static final String CITIES_ARE_DIFFERENT = "CITIES_ARE_DIFFERENT";
        public static final String RENT_TOTAL_PRICE_CALCULATED_SUCCESSFULLY = "RENT_TOTAL_PRICE_CALCULATED_SUCCESSFULLY";
        public static final String RENT_NOT_FOUND = "RENT_NOT_FOUND";
    }
}
