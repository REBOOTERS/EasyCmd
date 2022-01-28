package engineer.easy.dev.xml2json.bean

data class MaterialDownload(
    var materials: List<Material>, var setting: Setting, var token: String, var varid: Boolean, var version: Int
)

data class Material(
    var effectiveTimeRange: List<EffectiveTimeRange>,
    var enterLandingPageButton: EnterLandingPageButton,
    var exitButton: ExitButton,
    var landingPage: LandingPage,
    var materialId: String,
    var maxExposureTimes: Int,
    var monitorInfo: MonitorInfo,
    var playDuration: Int,
    var resourceZipMd5: String,
    var resourceZipUrl: String,
    var templateId: String,
    var uiControl: UiControl,
    var voiceInfo: VoiceInfo
)

data class Setting(
    var bootMethodSwitch: List<String>, var frequencyControlTimes: Int, var maxWaitMilliseconds: Int
)

data class EffectiveTimeRange(
    var endTimeStamp: Int, var startTimeStamp: Int
)

data class EnterLandingPageButton(
    var afterNetwork: AfterNetwork, var beforeNetwork: BeforeNetwork
)

data class ExitButton(
    var afterNetwork: AfterNetworkX, var beforeNetwork: BeforeNetworkX
)

data class LandingPage(
    var stayDuration: Int
)

data class MonitorInfo(
    var enterLandingPageUrl: String, var exitLandingPageUrl: String, var exitVideoUrl: String, var playVideoUrl: String
)

data class UiControl(
    var enterLandingPageQuery: List<String>, var exitQuery: List<String>, var followAdExitQuery: List<String>
)

data class VoiceInfo(
    var maxPlayVolume: Int, var muteSwitch: Boolean
)

data class AfterNetwork(
    var text: String
)

data class BeforeNetwork(
    var text: String
)

data class AfterNetworkX(
    var hit: String, var text: String
)

data class BeforeNetworkX(
    var text: String
)