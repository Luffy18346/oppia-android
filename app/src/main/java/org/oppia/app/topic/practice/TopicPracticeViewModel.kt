package org.oppia.app.topic.practice

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import org.oppia.app.fragment.FragmentScope
import org.oppia.app.model.Topic
import org.oppia.app.topic.RouteToQuestionPlayerListener
import org.oppia.app.topic.practice.practiceitemviewmodel.TopicPracticeFooterViewModel
import org.oppia.app.topic.practice.practiceitemviewmodel.TopicPracticeHeaderViewModel
import org.oppia.app.topic.practice.practiceitemviewmodel.TopicPracticeItemViewModel
import org.oppia.app.topic.practice.practiceitemviewmodel.TopicPracticeSkillSummaryViewModel
import org.oppia.domain.topic.TopicController
import org.oppia.util.data.AsyncResult
import org.oppia.util.logging.Logger
import javax.inject.Inject

/** The ViewModel for [TopicPracticeFragment]. */
@FragmentScope
class TopicPracticeViewModel @Inject constructor(
  activity: AppCompatActivity,
  private val logger: Logger,
  private val topicController: TopicController
) : ViewModel() {
  private val routeToQuestionPlayerListener = activity as RouteToQuestionPlayerListener
  private lateinit var topicId: String

  private val topicResultLiveData: LiveData<AsyncResult<Topic>> by lazy {
    topicController.getTopic(topicId)
  }

  private val topicLiveData: LiveData<Topic> by lazy { getTopicList() }

  private fun getTopicList(): LiveData<Topic> {
    return Transformations.map(topicResultLiveData, ::processTopicResult)
  }

  val topicPracticeSkillLiveData: LiveData<List<TopicPracticeItemViewModel>> by lazy {
    Transformations.map(topicLiveData, ::processTopicPracticeSkillList)
  }

  fun setTopicId(topicId: String) {
    this.topicId = topicId
  }

  private fun processTopicResult(topic: AsyncResult<Topic>): Topic {
    if (topic.isFailure()) {
      logger.e("TopicPracticeFragment", "Failed to retrieve topic", topic.getErrorOrNull()!!)
    }
    return topic.getOrDefault(Topic.getDefaultInstance())
  }

  private fun processTopicPracticeSkillList(topic: Topic): List<TopicPracticeItemViewModel> {

    // List starting with the header
    val itemViewModelList: MutableList<TopicPracticeItemViewModel> = mutableListOf(
      TopicPracticeHeaderViewModel() as TopicPracticeItemViewModel
    )

    // Add the Skills in the list
    itemViewModelList.addAll(topic.skillList.map { skill ->
      TopicPracticeSkillSummaryViewModel(skill) as TopicPracticeItemViewModel
    })

    // Add Footer in the list
    itemViewModelList.add(TopicPracticeFooterViewModel(routeToQuestionPlayerListener) as TopicPracticeItemViewModel)

    return itemViewModelList
  }

}
