package dynamicpool.dal.dto;

import java.util.ArrayList;

import dynamicpool.bll.model.ClientSetting;
import dynamicpool.bll.model.FishPackage;
import dynamicpool.bll.model.IFish;
import dynamicpool.bll.model.Segment;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class JSONContentDTO {
	private String jsonContent = null;

	public JSONContentDTO() {

	}

	public String getJsonContent() {
		return jsonContent;
	}

	public void setJsonContent(String jsonContent) {
		this.jsonContent = jsonContent;
	}
	
	public boolean equals(JSONContentDTO dto) {
		return this.jsonContent.equals(dto.jsonContent);
	}

	public static JSONContentDTO fromFish(IFish fish) {
		JSONContentDTO dto = new JSONContentDTO();
		String fishJson = new JSONSerializer().serialize(fish);
		dto.setJsonContent(fishJson);
		return dto;
	}

	public static IFish toFish(JSONContentDTO dto) throws Exception {
		try {
			String fishJson = dto.getJsonContent();
			return new JSONDeserializer<IFish>().deserialize(fishJson);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("cannot deserialize fish");
		}
	}
	
	public static JSONContentDTO fromFishPackage(FishPackage fishPackage) {
		JSONContentDTO dto = new JSONContentDTO();
		String jsonString = new JSONSerializer().serialize(fishPackage);
		dto.setJsonContent(jsonString);
		return dto;
	}
	
	public static FishPackage toFishPackage(JSONContentDTO dto) throws Exception {
		try {
			String jsonString = dto.getJsonContent();
			return new JSONDeserializer<FishPackage>().deserialize(jsonString);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("cannot deserialize fish package");
		}
	}
	
	public static JSONContentDTO fromClientSetting(ClientSetting clientSetting) {
		JSONContentDTO dto = new JSONContentDTO();
		String jsonString = new JSONSerializer().include("segments").serialize(clientSetting);
		dto.setJsonContent(jsonString);
		return dto;
	}
	
	public static ClientSetting toClientSetting(JSONContentDTO dto) throws Exception {
		try {
			String jsonString = dto.getJsonContent();
			ClientSetting clientSetting = new JSONDeserializer<ClientSetting>().deserialize(jsonString);
			if (clientSetting.getSegments() == null) {
				clientSetting.setSegments(new ArrayList<Segment>());
			}
			return clientSetting;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("cannot deserialize Setting");
		}
	}
}
