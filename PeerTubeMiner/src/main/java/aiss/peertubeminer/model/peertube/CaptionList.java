package aiss.peertubeminer.model.peertube;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CaptionList {

    @JsonProperty("data")
    private List<Caption> captions;

    @JsonProperty("data")
    public List<Caption> getCaptions() {
        return captions;
    }

    @JsonProperty("data")
    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }

    public CaptionList withCaptions(List<Caption> captions) {
        this.captions = captions;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CaptionList.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("captions");
        sb.append('=');
        sb.append(((this.captions == null)?"<null>":this.captions));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}