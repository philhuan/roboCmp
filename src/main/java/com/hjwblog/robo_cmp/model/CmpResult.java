package com.hjwblog.robo_cmp.model;

public class CmpResult {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cmp_result.id
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cmp_result.service
     *
     * @mbggenerated
     */
    private String service;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cmp_result.container
     *
     * @mbggenerated
     */
    private String container;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cmp_result.params
     *
     * @mbggenerated
     */
    private String params;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_cmp_result.result
     *
     * @mbggenerated
     */
    private String result;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cmp_result.id
     *
     * @return the value of t_cmp_result.id
     *
     * @mbggenerated
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cmp_result.id
     *
     * @param id the value for t_cmp_result.id
     *
     * @mbggenerated
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cmp_result.service
     *
     * @return the value of t_cmp_result.service
     *
     * @mbggenerated
     */
    public String getService() {
        return service;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cmp_result.service
     *
     * @param service the value for t_cmp_result.service
     *
     * @mbggenerated
     */
    public void setService(String service) {
        this.service = service == null ? null : service.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cmp_result.container
     *
     * @return the value of t_cmp_result.container
     *
     * @mbggenerated
     */
    public String getContainer() {
        return container;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cmp_result.container
     *
     * @param container the value for t_cmp_result.container
     *
     * @mbggenerated
     */
    public void setContainer(String container) {
        this.container = container == null ? null : container.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cmp_result.params
     *
     * @return the value of t_cmp_result.params
     *
     * @mbggenerated
     */
    public String getParams() {
        return params;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cmp_result.params
     *
     * @param params the value for t_cmp_result.params
     *
     * @mbggenerated
     */
    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_cmp_result.result
     *
     * @return the value of t_cmp_result.result
     *
     * @mbggenerated
     */
    public String getResult() {
        return result;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_cmp_result.result
     *
     * @param result the value for t_cmp_result.result
     *
     * @mbggenerated
     */
    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public CmpResult(String service, String container, String params, String result) {
        this.service = service;
        this.container = container;
        this.params = params;
        this.result = result;
    }

    public CmpResult() {
    }

}