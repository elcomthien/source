
/**
 * AdcenterAdminServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.elcom.adcenter.rvcadv.service;

    /**
     *  AdcenterAdminServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class AdcenterAdminServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public AdcenterAdminServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public AdcenterAdminServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getSessionid method
            * override this method for handling normal response from getSessionid operation
            */
           public void receiveResultgetSessionid(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.GetSessionidResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getSessionid operation
           */
            public void receiveErrorgetSessionid(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminDeleteSubjectContent method
            * override this method for handling normal response from adminDeleteSubjectContent operation
            */
           public void receiveResultadminDeleteSubjectContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDeleteSubjectContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDeleteSubjectContent operation
           */
            public void receiveErroradminDeleteSubjectContent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetPlayListGroup method
            * override this method for handling normal response from admingetPlayListGroup operation
            */
           public void receiveResultadmingetPlayListGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetPlayListGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetPlayListGroup operation
           */
            public void receiveErroradmingetPlayListGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUpdateLayoutCoor method
            * override this method for handling normal response from adminUpdateLayoutCoor operation
            */
           public void receiveResultadminUpdateLayoutCoor(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateLayoutCoorResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdateLayoutCoor operation
           */
            public void receiveErroradminUpdateLayoutCoor(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminNewGroup method
            * override this method for handling normal response from adminNewGroup operation
            */
           public void receiveResultadminNewGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminNewGroup operation
           */
            public void receiveErroradminNewGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetListGroup method
            * override this method for handling normal response from admingetListGroup operation
            */
           public void receiveResultadmingetListGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetListGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetListGroup operation
           */
            public void receiveErroradmingetListGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminReportStb method
            * override this method for handling normal response from adminReportStb operation
            */
           public void receiveResultadminReportStb(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportStbResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminReportStb operation
           */
            public void receiveErroradminReportStb(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetLayoutNameType method
            * override this method for handling normal response from admingetLayoutNameType operation
            */
           public void receiveResultadmingetLayoutNameType(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetLayoutNameTypeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetLayoutNameType operation
           */
            public void receiveErroradmingetLayoutNameType(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for main method
            * override this method for handling normal response from main operation
            */
           public void receiveResultmain(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from main operation
           */
            public void receiveErrormain(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUpdateSubjectContent method
            * override this method for handling normal response from adminUpdateSubjectContent operation
            */
           public void receiveResultadminUpdateSubjectContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateSubjectContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdateSubjectContent operation
           */
            public void receiveErroradminUpdateSubjectContent(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for adminDelItemPlaylist method
            * override this method for handling normal response from adminDelItemPlaylist operation
            */
           public void receiveResultadminDelItemPlaylist(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelItemPlaylistResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDelItemPlaylist operation
           */
            public void receiveErroradminDelItemPlaylist(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetTypeLayout method
            * override this method for handling normal response from admingetTypeLayout operation
            */
           public void receiveResultadmingetTypeLayout(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetTypeLayoutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetTypeLayout operation
           */
            public void receiveErroradmingetTypeLayout(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminReportContentAll method
            * override this method for handling normal response from adminReportContentAll operation
            */
           public void receiveResultadminReportContentAll(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportContentAllResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminReportContentAll operation
           */
            public void receiveErroradminReportContentAll(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for adminDelLayoutName method
            * override this method for handling normal response from adminDelLayoutName operation
            */
           public void receiveResultadminDelLayoutName(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelLayoutNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDelLayoutName operation
           */
            public void receiveErroradminDelLayoutName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUpdateGroup method
            * override this method for handling normal response from adminUpdateGroup operation
            */
           public void receiveResultadminUpdateGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdateGroup operation
           */
            public void receiveErroradminUpdateGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminDelGroup method
            * override this method for handling normal response from adminDelGroup operation
            */
           public void receiveResultadminDelGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDelGroup operation
           */
            public void receiveErroradminDelGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminNewSchedulePeriName method
            * override this method for handling normal response from adminNewSchedulePeriName operation
            */
           public void receiveResultadminNewSchedulePeriName(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewSchedulePeriNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminNewSchedulePeriName operation
           */
            public void receiveErroradminNewSchedulePeriName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminGetFullConFig method
            * override this method for handling normal response from adminGetFullConFig operation
            */
           public void receiveResultadminGetFullConFig(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetFullConFigResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminGetFullConFig operation
           */
            public void receiveErroradminGetFullConFig(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminNewLayoutContent method
            * override this method for handling normal response from adminNewLayoutContent operation
            */
           public void receiveResultadminNewLayoutContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewLayoutContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminNewLayoutContent operation
           */
            public void receiveErroradminNewLayoutContent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeContentStb method
            * override this method for handling normal response from removeContentStb operation
            */
           public void receiveResultremoveContentStb(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.RemoveContentStbResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeContentStb operation
           */
            public void receiveErrorremoveContentStb(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminGetPlaylistByGroupId method
            * override this method for handling normal response from adminGetPlaylistByGroupId operation
            */
           public void receiveResultadminGetPlaylistByGroupId(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetPlaylistByGroupIdResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminGetPlaylistByGroupId operation
           */
            public void receiveErroradminGetPlaylistByGroupId(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminDelSchduledailyTime method
            * override this method for handling normal response from adminDelSchduledailyTime operation
            */
           public void receiveResultadminDelSchduledailyTime(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelSchduledailyTimeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDelSchduledailyTime operation
           */
            public void receiveErroradminDelSchduledailyTime(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminRemoveSTBOutToGroup method
            * override this method for handling normal response from adminRemoveSTBOutToGroup operation
            */
           public void receiveResultadminRemoveSTBOutToGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminRemoveSTBOutToGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminRemoveSTBOutToGroup operation
           */
            public void receiveErroradminRemoveSTBOutToGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminStbPush method
            * override this method for handling normal response from adminStbPush operation
            */
           public void receiveResultadminStbPush(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminStbPushResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminStbPush operation
           */
            public void receiveErroradminStbPush(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for abopCheckUser method
            * override this method for handling normal response from abopCheckUser operation
            */
           public void receiveResultabopCheckUser(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopCheckUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopCheckUser operation
           */
            public void receiveErrorabopCheckUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminInserContentIntoGroup method
            * override this method for handling normal response from adminInserContentIntoGroup operation
            */
           public void receiveResultadminInserContentIntoGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminInserContentIntoGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminInserContentIntoGroup operation
           */
            public void receiveErroradminInserContentIntoGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUpdateScheduleDailyTime method
            * override this method for handling normal response from adminUpdateScheduleDailyTime operation
            */
           public void receiveResultadminUpdateScheduleDailyTime(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateScheduleDailyTimeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdateScheduleDailyTime operation
           */
            public void receiveErroradminUpdateScheduleDailyTime(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminInsertLayoutName method
            * override this method for handling normal response from adminInsertLayoutName operation
            */
           public void receiveResultadminInsertLayoutName(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminInsertLayoutNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminInsertLayoutName operation
           */
            public void receiveErroradminInsertLayoutName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetPlayListLayout method
            * override this method for handling normal response from admingetPlayListLayout operation
            */
           public void receiveResultadmingetPlayListLayout(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetPlayListLayoutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetPlayListLayout operation
           */
            public void receiveErroradmingetPlayListLayout(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetListGroups method
            * override this method for handling normal response from admingetListGroups operation
            */
           public void receiveResultadmingetListGroups(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetListGroupsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetListGroups operation
           */
            public void receiveErroradmingetListGroups(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getStbGroup method
            * override this method for handling normal response from getStbGroup operation
            */
           public void receiveResultgetStbGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.GetStbGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getStbGroup operation
           */
            public void receiveErrorgetStbGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminAddNewContentMedia method
            * override this method for handling normal response from adminAddNewContentMedia operation
            */
           public void receiveResultadminAddNewContentMedia(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddNewContentMediaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminAddNewContentMedia operation
           */
            public void receiveErroradminAddNewContentMedia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminDelStb method
            * override this method for handling normal response from adminDelStb operation
            */
           public void receiveResultadminDelStb(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelStbResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDelStb operation
           */
            public void receiveErroradminDelStb(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUpdateDirecGroup method
            * override this method for handling normal response from adminUpdateDirecGroup operation
            */
           public void receiveResultadminUpdateDirecGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateDirecGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdateDirecGroup operation
           */
            public void receiveErroradminUpdateDirecGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminNewContent method
            * override this method for handling normal response from adminNewContent operation
            */
           public void receiveResultadminNewContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminNewContent operation
           */
            public void receiveErroradminNewContent(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for adminUpdatePlaylistTime method
            * override this method for handling normal response from adminUpdatePlaylistTime operation
            */
           public void receiveResultadminUpdatePlaylistTime(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdatePlaylistTimeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdatePlaylistTime operation
           */
            public void receiveErroradminUpdatePlaylistTime(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminRemoveStbGroup method
            * override this method for handling normal response from adminRemoveStbGroup operation
            */
           public void receiveResultadminRemoveStbGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminRemoveStbGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminRemoveStbGroup operation
           */
            public void receiveErroradminRemoveStbGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminAddSTBIntoGroup method
            * override this method for handling normal response from adminAddSTBIntoGroup operation
            */
           public void receiveResultadminAddSTBIntoGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddSTBIntoGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminAddSTBIntoGroup operation
           */
            public void receiveErroradminAddSTBIntoGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminNewScheduleDaily method
            * override this method for handling normal response from adminNewScheduleDaily operation
            */
           public void receiveResultadminNewScheduleDaily(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewScheduleDailyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminNewScheduleDaily operation
           */
            public void receiveErroradminNewScheduleDaily(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetConfig method
            * override this method for handling normal response from admingetConfig operation
            */
           public void receiveResultadmingetConfig(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetConfigResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetConfig operation
           */
            public void receiveErroradmingetConfig(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetPlayList method
            * override this method for handling normal response from admingetPlayList operation
            */
           public void receiveResultadmingetPlayList(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetPlayListResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetPlayList operation
           */
            public void receiveErroradmingetPlayList(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for removeContentStbEach method
            * override this method for handling normal response from removeContentStbEach operation
            */
           public void receiveResultremoveContentStbEach(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.RemoveContentStbEachResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from removeContentStbEach operation
           */
            public void receiveErrorremoveContentStbEach(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for abopDeleteContentFromSTB method
            * override this method for handling normal response from abopDeleteContentFromSTB operation
            */
           public void receiveResultabopDeleteContentFromSTB(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopDeleteContentFromSTBResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopDeleteContentFromSTB operation
           */
            public void receiveErrorabopDeleteContentFromSTB(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminCheckStb method
            * override this method for handling normal response from adminCheckStb operation
            */
           public void receiveResultadminCheckStb(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminCheckStbResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminCheckStb operation
           */
            public void receiveErroradminCheckStb(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminDelLayOutItem method
            * override this method for handling normal response from adminDelLayOutItem operation
            */
           public void receiveResultadminDelLayOutItem(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelLayOutItemResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDelLayOutItem operation
           */
            public void receiveErroradminDelLayOutItem(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminNewPlaylistName method
            * override this method for handling normal response from adminNewPlaylistName operation
            */
           public void receiveResultadminNewPlaylistName(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewPlaylistNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminNewPlaylistName operation
           */
            public void receiveErroradminNewPlaylistName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetPlaylistItem method
            * override this method for handling normal response from admingetPlaylistItem operation
            */
           public void receiveResultadmingetPlaylistItem(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetPlaylistItemResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetPlaylistItem operation
           */
            public void receiveErroradmingetPlaylistItem(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetListStbAll method
            * override this method for handling normal response from admingetListStbAll operation
            */
           public void receiveResultadmingetListStbAll(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetListStbAllResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetListStbAll operation
           */
            public void receiveErroradmingetListStbAll(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for admingetLayoutName method
            * override this method for handling normal response from admingetLayoutName operation
            */
           public void receiveResultadmingetLayoutName(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetLayoutNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetLayoutName operation
           */
            public void receiveErroradmingetLayoutName(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for abopGetRole method
            * override this method for handling normal response from abopGetRole operation
            */
           public void receiveResultabopGetRole(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopGetRoleResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopGetRole operation
           */
            public void receiveErrorabopGetRole(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for abopUpdateUser method
            * override this method for handling normal response from abopUpdateUser operation
            */
           public void receiveResultabopUpdateUser(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopUpdateUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopUpdateUser operation
           */
            public void receiveErrorabopUpdateUser(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for adminGetAllContent method
            * override this method for handling normal response from adminGetAllContent operation
            */
           public void receiveResultadminGetAllContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetAllContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminGetAllContent operation
           */
            public void receiveErroradminGetAllContent(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for adminDeleteAllContentAllSTBInGroup method
            * override this method for handling normal response from adminDeleteAllContentAllSTBInGroup operation
            */
           public void receiveResultadminDeleteAllContentAllSTBInGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDeleteAllContentAllSTBInGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDeleteAllContentAllSTBInGroup operation
           */
            public void receiveErroradminDeleteAllContentAllSTBInGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for abopAddStbUser method
            * override this method for handling normal response from abopAddStbUser operation
            */
           public void receiveResultabopAddStbUser(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopAddStbUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopAddStbUser operation
           */
            public void receiveErrorabopAddStbUser(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for adminDeleteSlideContent method
            * override this method for handling normal response from adminDeleteSlideContent operation
            */
           public void receiveResultadminDeleteSlideContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDeleteSlideContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDeleteSlideContent operation
           */
            public void receiveErroradminDeleteSlideContent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminInsertLayout method
            * override this method for handling normal response from adminInsertLayout operation
            */
           public void receiveResultadminInsertLayout(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminInsertLayoutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminInsertLayout operation
           */
            public void receiveErroradminInsertLayout(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetContentNoGroup method
            * override this method for handling normal response from admingetContentNoGroup operation
            */
           public void receiveResultadmingetContentNoGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetContentNoGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetContentNoGroup operation
           */
            public void receiveErroradmingetContentNoGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminAddSubjectContent method
            * override this method for handling normal response from adminAddSubjectContent operation
            */
           public void receiveResultadminAddSubjectContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddSubjectContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminAddSubjectContent operation
           */
            public void receiveErroradminAddSubjectContent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminInsertPlaylistCore method
            * override this method for handling normal response from adminInsertPlaylistCore operation
            */
           public void receiveResultadminInsertPlaylistCore(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminInsertPlaylistCoreResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminInsertPlaylistCore operation
           */
            public void receiveErroradminInsertPlaylistCore(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for abopGetListUser method
            * override this method for handling normal response from abopGetListUser operation
            */
           public void receiveResultabopGetListUser(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopGetListUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopGetListUser operation
           */
            public void receiveErrorabopGetListUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for abopDeletePlaylistItems method
            * override this method for handling normal response from abopDeletePlaylistItems operation
            */
           public void receiveResultabopDeletePlaylistItems(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopDeletePlaylistItemsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopDeletePlaylistItems operation
           */
            public void receiveErrorabopDeletePlaylistItems(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminReportLayout method
            * override this method for handling normal response from adminReportLayout operation
            */
           public void receiveResultadminReportLayout(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportLayoutResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminReportLayout operation
           */
            public void receiveErroradminReportLayout(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for abopCreateUser method
            * override this method for handling normal response from abopCreateUser operation
            */
           public void receiveResultabopCreateUser(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopCreateUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopCreateUser operation
           */
            public void receiveErrorabopCreateUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminReportSchedulePeriod method
            * override this method for handling normal response from adminReportSchedulePeriod operation
            */
           public void receiveResultadminReportSchedulePeriod(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportSchedulePeriodResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminReportSchedulePeriod operation
           */
            public void receiveErroradminReportSchedulePeriod(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminGetAllSubjectContent method
            * override this method for handling normal response from adminGetAllSubjectContent operation
            */
           public void receiveResultadminGetAllSubjectContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetAllSubjectContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminGetAllSubjectContent operation
           */
            public void receiveErroradminGetAllSubjectContent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUpdateLayoutName method
            * override this method for handling normal response from adminUpdateLayoutName operation
            */
           public void receiveResultadminUpdateLayoutName(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateLayoutNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdateLayoutName operation
           */
            public void receiveErroradminUpdateLayoutName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUpdateSlideContent method
            * override this method for handling normal response from adminUpdateSlideContent operation
            */
           public void receiveResultadminUpdateSlideContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdateSlideContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdateSlideContent operation
           */
            public void receiveErroradminUpdateSlideContent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetSchedulePeriGroup method
            * override this method for handling normal response from admingetSchedulePeriGroup operation
            */
           public void receiveResultadmingetSchedulePeriGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetSchedulePeriGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetSchedulePeriGroup operation
           */
            public void receiveErroradmingetSchedulePeriGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetListStbAlls method
            * override this method for handling normal response from admingetListStbAlls operation
            */
           public void receiveResultadmingetListStbAlls(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetListStbAllsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetListStbAlls operation
           */
            public void receiveErroradmingetListStbAlls(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetScheduleDailyNamGroup method
            * override this method for handling normal response from admingetScheduleDailyNamGroup operation
            */
           public void receiveResultadmingetScheduleDailyNamGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetScheduleDailyNamGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetScheduleDailyNamGroup operation
           */
            public void receiveErroradmingetScheduleDailyNamGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for pushContentIntoSTBAuto method
            * override this method for handling normal response from pushContentIntoSTBAuto operation
            */
           public void receiveResultpushContentIntoSTBAuto(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.PushContentIntoSTBAutoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from pushContentIntoSTBAuto operation
           */
            public void receiveErrorpushContentIntoSTBAuto(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getContentGroup method
            * override this method for handling normal response from getContentGroup operation
            */
           public void receiveResultgetContentGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.GetContentGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getContentGroup operation
           */
            public void receiveErrorgetContentGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for abopDeleteUser method
            * override this method for handling normal response from abopDeleteUser operation
            */
           public void receiveResultabopDeleteUser(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopDeleteUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopDeleteUser operation
           */
            public void receiveErrorabopDeleteUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetLayoutContainContent method
            * override this method for handling normal response from admingetLayoutContainContent operation
            */
           public void receiveResultadmingetLayoutContainContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetLayoutContainContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetLayoutContainContent operation
           */
            public void receiveErroradmingetLayoutContainContent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminNewScheduleDailyName method
            * override this method for handling normal response from adminNewScheduleDailyName operation
            */
           public void receiveResultadminNewScheduleDailyName(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminNewScheduleDailyNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminNewScheduleDailyName operation
           */
            public void receiveErroradminNewScheduleDailyName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUpdatePlayListName method
            * override this method for handling normal response from adminUpdatePlayListName operation
            */
           public void receiveResultadminUpdatePlayListName(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUpdatePlayListNameResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUpdatePlayListName operation
           */
            public void receiveErroradminUpdatePlayListName(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminReportPlaylist method
            * override this method for handling normal response from adminReportPlaylist operation
            */
           public void receiveResultadminReportPlaylist(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportPlaylistResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminReportPlaylist operation
           */
            public void receiveErroradminReportPlaylist(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetItemScheduleDailyForPerio method
            * override this method for handling normal response from admingetItemScheduleDailyForPerio operation
            */
           public void receiveResultadmingetItemScheduleDailyForPerio(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetItemScheduleDailyForPerioResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetItemScheduleDailyForPerio operation
           */
            public void receiveErroradmingetItemScheduleDailyForPerio(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminDeleteContentIntoGroup method
            * override this method for handling normal response from adminDeleteContentIntoGroup operation
            */
           public void receiveResultadminDeleteContentIntoGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDeleteContentIntoGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDeleteContentIntoGroup operation
           */
            public void receiveErroradminDeleteContentIntoGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminNewContentLogic method
            * override this method for handling normal response from adminNewContentLogic operation
            */
           public void receiveResultadminNewContentLogic(
                    ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminNewContentLogic operation
           */
            public void receiveErroradminNewContentLogic(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetScheduleDailyGroup method
            * override this method for handling normal response from admingetScheduleDailyGroup operation
            */
           public void receiveResultadmingetScheduleDailyGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetScheduleDailyGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetScheduleDailyGroup operation
           */
            public void receiveErroradmingetScheduleDailyGroup(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for abopSetMonitoring method
            * override this method for handling normal response from abopSetMonitoring operation
            */
           public void receiveResultabopSetMonitoring(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopSetMonitoringResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopSetMonitoring operation
           */
            public void receiveErrorabopSetMonitoring(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminGetAllContentMedia method
            * override this method for handling normal response from adminGetAllContentMedia operation
            */
           public void receiveResultadminGetAllContentMedia(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetAllContentMediaResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminGetAllContentMedia operation
           */
            public void receiveErroradminGetAllContentMedia(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetLayoutGroup method
            * override this method for handling normal response from admingetLayoutGroup operation
            */
           public void receiveResultadmingetLayoutGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetLayoutGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetLayoutGroup operation
           */
            public void receiveErroradmingetLayoutGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for admingetContentStb method
            * override this method for handling normal response from admingetContentStb operation
            */
           public void receiveResultadmingetContentStb(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdmingetContentStbResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from admingetContentStb operation
           */
            public void receiveErroradmingetContentStb(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for adminMoveGroup method
            * override this method for handling normal response from adminMoveGroup operation
            */
           public void receiveResultadminMoveGroup(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminMoveGroupResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminMoveGroup operation
           */
            public void receiveErroradminMoveGroup(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminDelPlaylist method
            * override this method for handling normal response from adminDelPlaylist operation
            */
           public void receiveResultadminDelPlaylist(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminDelPlaylistResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminDelPlaylist operation
           */
            public void receiveErroradminDelPlaylist(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminAddNewSlideContent method
            * override this method for handling normal response from adminAddNewSlideContent operation
            */
           public void receiveResultadminAddNewSlideContent(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminAddNewSlideContentResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminAddNewSlideContent operation
           */
            public void receiveErroradminAddNewSlideContent(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminUdateStb method
            * override this method for handling normal response from adminUdateStb operation
            */
           public void receiveResultadminUdateStb(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminUdateStbResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminUdateStb operation
           */
            public void receiveErroradminUdateStb(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminGetContentIDByForSlide method
            * override this method for handling normal response from adminGetContentIDByForSlide operation
            */
           public void receiveResultadminGetContentIDByForSlide(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminGetContentIDByForSlideResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminGetContentIDByForSlide operation
           */
            public void receiveErroradminGetContentIDByForSlide(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for abopGetUser method
            * override this method for handling normal response from abopGetUser operation
            */
           public void receiveResultabopGetUser(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AbopGetUserResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from abopGetUser operation
           */
            public void receiveErrorabopGetUser(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for adminReportDaily method
            * override this method for handling normal response from adminReportDaily operation
            */
           public void receiveResultadminReportDaily(
                    com.elcom.adcenter.rvcadv.service.AdcenterAdminServiceStub.AdminReportDailyResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from adminReportDaily operation
           */
            public void receiveErroradminReportDaily(java.lang.Exception e) {
            }
                


    }
    