package com.actionlistapi.model;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnumValue;

public enum ActionType {
	
	 @XmlEnumValue("k") SU_ACKNOWLEDGE("k", "Super User Acknowledge"),
	    
	    @XmlEnumValue("f") SU_FYI("f", "Super User FYI"),
	    
	    @XmlEnumValue("m") SU_COMPLETE("m", "Super User Complete"),
	    
	    @XmlEnumValue("v") SU_APPROVE("v", "Super User Approve"),
	    
	    @XmlEnumValue("r") SU_ROUTE_NODE_APPROVE("r", "Super User Route Node Approve"),
	    
	    @XmlEnumValue("z") SU_RETURN_TO_PREVIOUS("z", "Super User Return To Previous"),
	    
	    @XmlEnumValue("d") SU_DISAPPROVE("d", "Super User Disapprove"),
	    
	    @XmlEnumValue("c") SU_CANCEL("c", "Super User Cancel"),
	    
	    @XmlEnumValue("a") SU_BLANKET_APPROVE("a", "Super User Blanket Approve"),
	    
	    @XmlEnumValue("B") BLANKET_APPROVE("B", "Blanket Approve"),
	    
	    @XmlEnumValue("F") FYI("F", "FYI"),
	    
	    /**
	     * User has generated an action request to another user
	     */
	    @XmlEnumValue("H") ADHOC_REQUEST("H", "Adhoc Request"),
	    
	    /**
	     * AdHoc Request has been revoked
	     */
	    @XmlEnumValue("V") ADHOC_REQUEST_REVOKE("V", "Adhoc Request_Revoke"),
	    
	    /**
	     * Document has been saved by the user for later work
	     */
	    @XmlEnumValue("S") SAVE("S", "Saved"),
	    
	    /**
	     * Document has been canceled.
	     */
	    @XmlEnumValue("X") CANCEL("X", "Cancel"),
	    
	    /**
	     * Document has been disapproved.
	     */
	    @XmlEnumValue("D") DISAPPROVE("D", "Disapprove"),
	    
	    /**
	     * Document has been opened by the designated recipient.
	     */
	    @XmlEnumValue("K") ACKNOWLEDGE("K", "Acknowledge"),
	    
	    /**
	     * Document has been completed as requested.
	     */
	    @XmlEnumValue("C") COMPLETE("C", "Complete"),
	    
	    /**
	     * Document has been submitted to the engine for processing.
	     */
	    @XmlEnumValue("O") ROUTE("O", "Route"),
	    
	    /**
	     * The document has been approved.
	     */
	    @XmlEnumValue("A") APPROVE("A", "Approve"),
	    
	    /**
	     * The document is being returned to a previous routelevel
	     */
	    @XmlEnumValue("Z") RETURN_TO_PREVIOUS("Z", "Return To Previous"),

	    /**
	     * The document is being recalled.
	     * @since 2.1
	     */
	    @XmlEnumValue("L") RECALL("L", "Recall"),

	    /**
	     * The document has non-routed activity against it that is recorded in the route log
	     */
	    @XmlEnumValue("R") LOG_MESSAGE("R", "Log Message"),
	    
	    /**
	     * The document is routed to a group and a user in the group wants to take authority from the group
	     */
	    @XmlEnumValue("w") TAKE_GROUP_AUTHORITY("w", "Take Group Authority"),
	    		
	    /**
	     * The person who took group authority is releasing it
	     */
	    @XmlEnumValue("y") RELEASE_GROUP_AUTHORITY("y", "Release Group Authority"),
	    
	    /**
	     * The document is moved
	     */
	    @XmlEnumValue("M") MOVE("M", "Moved");

	    /**
	     * Map of ActionTypes to their SuperUser equivalent
	     */
	    private static final Map<ActionType, ActionType> SU_ACTION_TYPE = new HashMap<ActionType, ActionType>();
	    static {
	        SU_ACTION_TYPE.put(APPROVE, SU_APPROVE);
	        SU_ACTION_TYPE.put(COMPLETE, SU_COMPLETE);
	        SU_ACTION_TYPE.put(FYI, SU_FYI);
	        SU_ACTION_TYPE.put(ACKNOWLEDGE, SU_ACKNOWLEDGE);
	    }

	    private final String code;
		private final String label;
		
		private ActionType(String code, String label) {
			this.code = code;
			this.label = label;
		}
		
		
		public String getLabel() {
			return label;
		}
		
		
		public String getCode() {
			return code;
		}
		
		
		public static ActionType fromCode(String code) {
			return fromCode(code, false);
		}
		
		public static ActionType fromCode(String code, boolean allowMissing) {
			if (code == null) {
				return null;
			}
			for (ActionType status : values()) {
				if (status.code.equals(code)) {
					return status;
				}
			}
			if (allowMissing) {
				return null;
			}
			throw new IllegalArgumentException("Failed to locate the ActionType with the given code: " + code);
		}

	    public static ActionType fromLabel(String label) {
	        if (label == null) {
	            return null;
	        }
	        for (ActionType status : values()) {
	            if (status.label.equals(label)) {
	                return status;
	            }
	        }
	        return null;
	    }

	    public static ActionType fromCodeOrLabel(String str) {
	        ActionType at = ActionType.fromCode(str, true);
	        if (at == null && str != null) {
	            at = ActionType.fromLabel(str.toUpperCase());
	        }
	        return at;
	    }

	    /**
	     * Converts a non-super-user ActionType to its SuperUser equivalent
	     * @since 2.1.3
	     * @param at the ActionType
	     * @return super-user version of ActionType or null if no equivalent SU action type
	     */
	    public static ActionType toSuperUserActionType(ActionType at) {
	        if (SU_ACTION_TYPE.containsKey(at)) return at;
	        return SU_ACTION_TYPE.get(at);
	    }


}