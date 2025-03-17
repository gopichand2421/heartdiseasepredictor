package com.heartpredictorservice.model;

public class PatientReport {

    private Integer id;
    //age,sex,cp,trestbps,chol,fbs,restecg,thalach,exang,oldpeak,slope,ca,thal,target

    /**
     * <h5>Age</h5>
     * <p>Age of the patient in years</p>
     */
    private Integer age;

    private String tile;
    /**
     * <h5>Gender</h5>
     * <p>Gender of the patient(0 = male, 1=Female)</p>
     */
    private String gender;
    /**
     * <h5>Chest pain type</h5>
     * 0: Typical angina</br>
     * 1: Atypical angina</br>
     * 2: Non-anginal pain</br>
     * 3: Asymptomatic
     */
    private Integer chestpain;

    /**
     * Resting Blood pressure in mm Hg
     */
    private Integer trestbp;

    /**
     * <h5>Serum cholesterol in mg/dl</h5>
     */
    private Integer chol;

    /**
     * <h5>Fasting blood sugar level, categorized as above 120mg/dl(1=true, 0=false)</h5>
     */
    private Boolean fbs;

    /**
     * <h5>Resting electrocardiographic results</h5>
     * <p>0: Normal</p>
     * <p>1: Having ST-T wave abnormality</p>
     * <p>2: showing probable or definite left ventricular hypertrophy</p>
     */
    private Integer restecg;

    /**
     * <h5>Maximum heart rate achieved during a stress test</h5>
     */
    private Integer thalach;

    /**
     * <h5>Exercise induced angina(1=yes,0=no)</h5>
     */
    private Boolean exang;

    /**
     * <h5>ST depression induced by exercise relative to rest</h5>
     */
    private Float oldpeak;

    /**
     * <h5>Slope of the peak exercise relative to rest</h5>
     * 0: Upsloping</br>
     * 1: Flat</br>
     * 2: Downsloping
     */
    private Integer sloap;

    /**
     * Number of major vessels(0-4) colored by fluroroscopy
     */
    private Integer ca;

    /**
     * <h5>Thalium stress test result</h5>
     * 0: Normal</br>
     * 1: Fixed defect</br>
     * 2: Reversible Defect</br>
     * 3: Not Described</br>
     */
    private Integer thal;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getChestpain() {
        return chestpain;
    }

    public void setChestpain(Integer chestpain) {
        this.chestpain = chestpain;
    }

    public Integer getTrestbp() {
        return trestbp;
    }

    public void setTrestbp(Integer trestbp) {
        this.trestbp = trestbp;
    }

    public Integer getChol() {
        return chol;
    }

    public void setChol(Integer chol) {
        this.chol = chol;
    }

    public Boolean getFbs() {
        return fbs;
    }

    public void setFbs(Boolean fbs) {
        this.fbs = fbs;
    }

    public Integer getRestecg() {
        return restecg;
    }

    public void setRestecg(Integer restecg) {
        this.restecg = restecg;
    }

    public Integer getThalach() {
        return thalach;
    }

    public void setThalach(Integer thalach) {
        this.thalach = thalach;
    }

    public Boolean getExang() {
        return exang;
    }

    public void setExang(Boolean exang) {
        this.exang = exang;
    }

    public Float getOldpeak() {
        return oldpeak;
    }

    public void setOldpeak(Float oldpeak) {
        this.oldpeak = oldpeak;
    }

    public Integer getSloap() {
        return sloap;
    }

    public void setSloap(Integer sloap) {
        this.sloap = sloap;
    }

    public Integer getCa() {
        return ca;
    }

    public void setCa(Integer ca) {
        this.ca = ca;
    }

    public Integer getThal() {
        return thal;
    }

    public void setThal(Integer thal) {
        this.thal = thal;
    }
}
