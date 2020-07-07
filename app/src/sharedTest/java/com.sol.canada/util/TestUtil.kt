package com.sol.canada.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sol.canada.ui.countryfactdetails.data.Fact
import com.sol.canada.ui.countryfactdetails.data.FactDetail
import com.sol.canada.data.Result

object TestUtil {
    var testCaseSuccess:Boolean = false
    val factResult =  Fact(rows= listOf(FactDetail(id=0, description="Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony", imageHref="http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg", title="Beavers"), FactDetail(id=0, description=null, imageHref="http://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png", title="Flag"), FactDetail(id=0, description="It is a well known fact that polar bears are the main mode of transportation in Canada. They consume far less gas and have the added benefit of being difficult to steal.", imageHref="http://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg", title="Transportation"), FactDetail(id=0, description="These Saturday night CBC broadcasts originally aired on radio in 1931. In 1952 they debuted on television and continue to unite (and divide) the nation each week.", imageHref="http://fyimusic.ca/wp-content/uploads/2008/06/hockey-night-in-canada.thumbnail.jpg", title="Hockey Night in Canada"), FactDetail(id=0, description="A chiefly Canadian interrogative utterance, usually expressing surprise or doubt or seeking confirmation.", imageHref=null, title="Eh"), FactDetail(id=0, description="Warmer than you might think.", imageHref="http://icons.iconarchive.com/icons/iconshock/alaska/256/Igloo-icon.png", title="Housing"), FactDetail(id=0, description= "Sadly it's true.", imageHref="http://static.guim.co.uk/sys-images/Music/Pix/site_furniture/2007/04/19/avril_lavigne.jpg", title="Public Shame"), FactDetail(id=0, description=null, imageHref=null, title=null), FactDetail(id=0, description="Canada hopes to soon launch a man to the moon.", imageHref="http://files.turbosquid.com/Preview/Content_2009_07_14__10_25_15/trebucheta.jpgdf3f3bf4-935d-40ff-84b2-6ce718a327a9Larger.jpg", title="Space Program"), FactDetail(id=0, description="A moose is a common sight in Canada. Tall and majestic, they represent many of the values which Canadians imagine that they possess. They grow up to 2.7 metres long and can weigh over 700 kg. They swim at 10 km/h. Moose antlers weigh roughly 20 kg. The plural of moose is actually 'meese', despite what most dictionaries, encyclopedias, and experts will tell you.", imageHref="http://caroldeckerwildlifeartstudio.net/wp-content/uploads/2011/04/IMG_2418%20majestic%20moose%201%20copy%20(Small)-96x96.jpg", title="Meese"), FactDetail(id=0, description="It's really big.", imageHref=null, title="Geography"), FactDetail(id=0, description="Éare illegal. Cats are fine.", imageHref="http://www.donegalhimalayans.com/images/That%20fish%20was%20this%20big.jpg", title="Kittens..."), FactDetail(id=0, description="They are the law. They are also Canada's foreign espionage service. Subtle.", imageHref="http://3.bp.blogspot.com/__mokxbTmuJM/RnWuJ6cE9cI/AAAAAAAAATw/6z3m3w9JDiU/s400/019843_31.jpg", title="Mounties"), FactDetail(id=0, description="Nous parlons tous les langues importants.", imageHref=null, title="Language")), title="About Canada")
    private val factErrorResult =  "Network call has failed for a following reason: Unable to resolve host \"dl.dropboxusercontent.com\": No address associated with hostname"
    var factMutableLiveData:MutableLiveData<Result<List<FactDetail>>> = MutableLiveData()
    fun getFactsDetail():LiveData<Result<List<FactDetail>>>{
        if(testCaseSuccess) {
            factMutableLiveData.value = Result.success(factResult.rows)
        }else{
            factMutableLiveData.value = Result.error(factErrorResult)
        }
        return factMutableLiveData
    }
}