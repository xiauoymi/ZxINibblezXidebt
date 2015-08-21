/**
 * Copyright 2015-2016. All rights reserved by Nibbledebt Inc.
 */
package com.nibbledebt.integration.model.mandrill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Generated object representation of the message that can be added
 * to the send request of the Mandrill connector.
 * 
 * @author ralam1
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "html",
    "text",
    "subject",
    "from_email",
    "from_name",
    "to",
    "headers",
    "important",
    "track_opens",
    "track_clicks",
    "auto_text",
    "auto_html",
    "inline_css",
    "url_strip_qs",
    "preserve_recipients",
    "view_content_link",
    "bcc_address",
    "tracking_domain",
    "signing_domain",
    "return_path_domain",
    "merge",
    "merge_language",
    "global_merge_vars",
    "merge_vars",
    "tags",
    "subaccount",
    "google_analytics_domains",
    "google_analytics_campaign",
    "metadata",
    "recipient_metadata",
    "attachments",
    "images"
})
public class Message {

    @JsonProperty("html")
    private String html;
    @JsonProperty("text")
    private String text;
    @JsonProperty("subject")
    private String subject;
    @JsonProperty("from_email")
    private String fromEmail;
    @JsonProperty("from_name")
    private String fromName;
    @JsonProperty("to")
    private List<To> to = new ArrayList<To>();
    @JsonProperty("headers")
    private Headers headers;
    @JsonProperty("important")
    private Boolean important;
    @JsonProperty("track_opens")
    private Object trackOpens;
    @JsonProperty("track_clicks")
    private Object trackClicks;
    @JsonProperty("auto_text")
    private Object autoText;
    @JsonProperty("auto_html")
    private Object autoHtml;
    @JsonProperty("inline_css")
    private Object inlineCss;
    @JsonProperty("url_strip_qs")
    private Object urlStripQs;
    @JsonProperty("preserve_recipients")
    private Object preserveRecipients;
    @JsonProperty("view_content_link")
    private Object viewContentLink;
    @JsonProperty("bcc_address")
    private String bccAddress;
    @JsonProperty("tracking_domain")
    private Object trackingDomain;
    @JsonProperty("signing_domain")
    private Object signingDomain;
    @JsonProperty("return_path_domain")
    private Object returnPathDomain;
    @JsonProperty("merge")
    private Boolean merge;
    @JsonProperty("merge_language")
    private String mergeLanguage;
    @JsonProperty("global_merge_vars")
    private List<GlobalMergeVar> globalMergeVars = new ArrayList<GlobalMergeVar>();
    @JsonProperty("merge_vars")
    private List<MergeVar> mergeVars = new ArrayList<MergeVar>();
    @JsonProperty("tags")
    private List<String> tags = new ArrayList<String>();
    @JsonProperty("subaccount")
    private String subaccount;
    @JsonProperty("google_analytics_domains")
    private List<String> googleAnalyticsDomains = new ArrayList<String>();
    @JsonProperty("google_analytics_campaign")
    private String googleAnalyticsCampaign;
    @JsonProperty("metadata")
    private Metadata metadata;
    @JsonProperty("recipient_metadata")
    private List<RecipientMetadatum> recipientMetadata = new ArrayList<RecipientMetadatum>();
    @JsonProperty("attachments")
    private List<Attachment> attachments = new ArrayList<Attachment>();
    @JsonProperty("images")
    private List<Image> images = new ArrayList<Image>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The html
     */
    @JsonProperty("html")
    public String getHtml() {
        return html;
    }

    /**
     * 
     * @param html
     *     The html
     */
    @JsonProperty("html")
    public void setHtml(String html) {
        this.html = html;
    }

    /**
     * 
     * @return
     *     The text
     */
    @JsonProperty("text")
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The subject
     */
    @JsonProperty("subject")
    public String getSubject() {
        return subject;
    }

    /**
     * 
     * @param subject
     *     The subject
     */
    @JsonProperty("subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 
     * @return
     *     The fromEmail
     */
    @JsonProperty("from_email")
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * 
     * @param fromEmail
     *     The from_email
     */
    @JsonProperty("from_email")
    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    /**
     * 
     * @return
     *     The fromName
     */
    @JsonProperty("from_name")
    public String getFromName() {
        return fromName;
    }

    /**
     * 
     * @param fromName
     *     The from_name
     */
    @JsonProperty("from_name")
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * 
     * @return
     *     The to
     */
    @JsonProperty("to")
    public List<To> getTo() {
        return to;
    }

    /**
     * 
     * @param to
     *     The to
     */
    @JsonProperty("to")
    public void setTo(List<To> to) {
        this.to = to;
    }

    /**
     * 
     * @return
     *     The headers
     */
    @JsonProperty("headers")
    public Headers getHeaders() {
        return headers;
    }

    /**
     * 
     * @param headers
     *     The headers
     */
    @JsonProperty("headers")
    public void setHeaders(Headers headers) {
        this.headers = headers;
    }

    /**
     * 
     * @return
     *     The important
     */
    @JsonProperty("important")
    public Boolean getImportant() {
        return important;
    }

    /**
     * 
     * @param important
     *     The important
     */
    @JsonProperty("important")
    public void setImportant(Boolean important) {
        this.important = important;
    }

    /**
     * 
     * @return
     *     The trackOpens
     */
    @JsonProperty("track_opens")
    public Object getTrackOpens() {
        return trackOpens;
    }

    /**
     * 
     * @param trackOpens
     *     The track_opens
     */
    @JsonProperty("track_opens")
    public void setTrackOpens(Object trackOpens) {
        this.trackOpens = trackOpens;
    }

    /**
     * 
     * @return
     *     The trackClicks
     */
    @JsonProperty("track_clicks")
    public Object getTrackClicks() {
        return trackClicks;
    }

    /**
     * 
     * @param trackClicks
     *     The track_clicks
     */
    @JsonProperty("track_clicks")
    public void setTrackClicks(Object trackClicks) {
        this.trackClicks = trackClicks;
    }

    /**
     * 
     * @return
     *     The autoText
     */
    @JsonProperty("auto_text")
    public Object getAutoText() {
        return autoText;
    }

    /**
     * 
     * @param autoText
     *     The auto_text
     */
    @JsonProperty("auto_text")
    public void setAutoText(Object autoText) {
        this.autoText = autoText;
    }

    /**
     * 
     * @return
     *     The autoHtml
     */
    @JsonProperty("auto_html")
    public Object getAutoHtml() {
        return autoHtml;
    }

    /**
     * 
     * @param autoHtml
     *     The auto_html
     */
    @JsonProperty("auto_html")
    public void setAutoHtml(Object autoHtml) {
        this.autoHtml = autoHtml;
    }

    /**
     * 
     * @return
     *     The inlineCss
     */
    @JsonProperty("inline_css")
    public Object getInlineCss() {
        return inlineCss;
    }

    /**
     * 
     * @param inlineCss
     *     The inline_css
     */
    @JsonProperty("inline_css")
    public void setInlineCss(Object inlineCss) {
        this.inlineCss = inlineCss;
    }

    /**
     * 
     * @return
     *     The urlStripQs
     */
    @JsonProperty("url_strip_qs")
    public Object getUrlStripQs() {
        return urlStripQs;
    }

    /**
     * 
     * @param urlStripQs
     *     The url_strip_qs
     */
    @JsonProperty("url_strip_qs")
    public void setUrlStripQs(Object urlStripQs) {
        this.urlStripQs = urlStripQs;
    }

    /**
     * 
     * @return
     *     The preserveRecipients
     */
    @JsonProperty("preserve_recipients")
    public Object getPreserveRecipients() {
        return preserveRecipients;
    }

    /**
     * 
     * @param preserveRecipients
     *     The preserve_recipients
     */
    @JsonProperty("preserve_recipients")
    public void setPreserveRecipients(Object preserveRecipients) {
        this.preserveRecipients = preserveRecipients;
    }

    /**
     * 
     * @return
     *     The viewContentLink
     */
    @JsonProperty("view_content_link")
    public Object getViewContentLink() {
        return viewContentLink;
    }

    /**
     * 
     * @param viewContentLink
     *     The view_content_link
     */
    @JsonProperty("view_content_link")
    public void setViewContentLink(Object viewContentLink) {
        this.viewContentLink = viewContentLink;
    }

    /**
     * 
     * @return
     *     The bccAddress
     */
    @JsonProperty("bcc_address")
    public String getBccAddress() {
        return bccAddress;
    }

    /**
     * 
     * @param bccAddress
     *     The bcc_address
     */
    @JsonProperty("bcc_address")
    public void setBccAddress(String bccAddress) {
        this.bccAddress = bccAddress;
    }

    /**
     * 
     * @return
     *     The trackingDomain
     */
    @JsonProperty("tracking_domain")
    public Object getTrackingDomain() {
        return trackingDomain;
    }

    /**
     * 
     * @param trackingDomain
     *     The tracking_domain
     */
    @JsonProperty("tracking_domain")
    public void setTrackingDomain(Object trackingDomain) {
        this.trackingDomain = trackingDomain;
    }

    /**
     * 
     * @return
     *     The signingDomain
     */
    @JsonProperty("signing_domain")
    public Object getSigningDomain() {
        return signingDomain;
    }

    /**
     * 
     * @param signingDomain
     *     The signing_domain
     */
    @JsonProperty("signing_domain")
    public void setSigningDomain(Object signingDomain) {
        this.signingDomain = signingDomain;
    }

    /**
     * 
     * @return
     *     The returnPathDomain
     */
    @JsonProperty("return_path_domain")
    public Object getReturnPathDomain() {
        return returnPathDomain;
    }

    /**
     * 
     * @param returnPathDomain
     *     The return_path_domain
     */
    @JsonProperty("return_path_domain")
    public void setReturnPathDomain(Object returnPathDomain) {
        this.returnPathDomain = returnPathDomain;
    }

    /**
     * 
     * @return
     *     The merge
     */
    @JsonProperty("merge")
    public Boolean getMerge() {
        return merge;
    }

    /**
     * 
     * @param merge
     *     The merge
     */
    @JsonProperty("merge")
    public void setMerge(Boolean merge) {
        this.merge = merge;
    }

    /**
     * 
     * @return
     *     The mergeLanguage
     */
    @JsonProperty("merge_language")
    public String getMergeLanguage() {
        return mergeLanguage;
    }

    /**
     * 
     * @param mergeLanguage
     *     The merge_language
     */
    @JsonProperty("merge_language")
    public void setMergeLanguage(String mergeLanguage) {
        this.mergeLanguage = mergeLanguage;
    }

    /**
     * 
     * @return
     *     The globalMergeVars
     */
    @JsonProperty("global_merge_vars")
    public List<GlobalMergeVar> getGlobalMergeVars() {
        return globalMergeVars;
    }

    /**
     * 
     * @param globalMergeVars
     *     The global_merge_vars
     */
    @JsonProperty("global_merge_vars")
    public void setGlobalMergeVars(List<GlobalMergeVar> globalMergeVars) {
        this.globalMergeVars = globalMergeVars;
    }

    /**
     * 
     * @return
     *     The mergeVars
     */
    @JsonProperty("merge_vars")
    public List<MergeVar> getMergeVars() {
        return mergeVars;
    }

    /**
     * 
     * @param mergeVars
     *     The merge_vars
     */
    @JsonProperty("merge_vars")
    public void setMergeVars(List<MergeVar> mergeVars) {
        this.mergeVars = mergeVars;
    }

    /**
     * 
     * @return
     *     The tags
     */
    @JsonProperty("tags")
    public List<String> getTags() {
        return tags;
    }

    /**
     * 
     * @param tags
     *     The tags
     */
    @JsonProperty("tags")
    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * 
     * @return
     *     The subaccount
     */
    @JsonProperty("subaccount")
    public String getSubaccount() {
        return subaccount;
    }

    /**
     * 
     * @param subaccount
     *     The subaccount
     */
    @JsonProperty("subaccount")
    public void setSubaccount(String subaccount) {
        this.subaccount = subaccount;
    }

    /**
     * 
     * @return
     *     The googleAnalyticsDomains
     */
    @JsonProperty("google_analytics_domains")
    public List<String> getGoogleAnalyticsDomains() {
        return googleAnalyticsDomains;
    }

    /**
     * 
     * @param googleAnalyticsDomains
     *     The google_analytics_domains
     */
    @JsonProperty("google_analytics_domains")
    public void setGoogleAnalyticsDomains(List<String> googleAnalyticsDomains) {
        this.googleAnalyticsDomains = googleAnalyticsDomains;
    }

    /**
     * 
     * @return
     *     The googleAnalyticsCampaign
     */
    @JsonProperty("google_analytics_campaign")
    public String getGoogleAnalyticsCampaign() {
        return googleAnalyticsCampaign;
    }

    /**
     * 
     * @param googleAnalyticsCampaign
     *     The google_analytics_campaign
     */
    @JsonProperty("google_analytics_campaign")
    public void setGoogleAnalyticsCampaign(String googleAnalyticsCampaign) {
        this.googleAnalyticsCampaign = googleAnalyticsCampaign;
    }

    /**
     * 
     * @return
     *     The metadata
     */
    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    /**
     * 
     * @param metadata
     *     The metadata
     */
    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    /**
     * 
     * @return
     *     The recipientMetadata
     */
    @JsonProperty("recipient_metadata")
    public List<RecipientMetadatum> getRecipientMetadata() {
        return recipientMetadata;
    }

    /**
     * 
     * @param recipientMetadata
     *     The recipient_metadata
     */
    @JsonProperty("recipient_metadata")
    public void setRecipientMetadata(List<RecipientMetadatum> recipientMetadata) {
        this.recipientMetadata = recipientMetadata;
    }

    /**
     * 
     * @return
     *     The attachments
     */
    @JsonProperty("attachments")
    public List<Attachment> getAttachments() {
        return attachments;
    }

    /**
     * 
     * @param attachments
     *     The attachments
     */
    @JsonProperty("attachments")
    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    /**
     * 
     * @return
     *     The images
     */
    @JsonProperty("images")
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    @JsonProperty("images")
    public void setImages(List<Image> images) {
        this.images = images;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
