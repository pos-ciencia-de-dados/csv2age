package tcc.cypher;

import tcc.cypher.impl.dynamic.CypherComment;
import tcc.cypher.impl.dynamic.CypherCommentHasCreatorPerson;
import tcc.cypher.impl.dynamic.CypherCommentHasTagTag;
import tcc.cypher.impl.dynamic.CypherCommentIsLocatedInPlace;
import tcc.cypher.impl.dynamic.CypherCommentReplyOfComment;
import tcc.cypher.impl.dynamic.CypherCommentReplyOfPost;
import tcc.cypher.impl.dynamic.CypherForum;
import tcc.cypher.impl.dynamic.CypherForumContainerOfPost;
import tcc.cypher.impl.dynamic.CypherForumHasMemberPerson;
import tcc.cypher.impl.dynamic.CypherForumHasModeratorPerson;
import tcc.cypher.impl.dynamic.CypherForumHasTagTag;
import tcc.cypher.impl.dynamic.CypherPerson;
import tcc.cypher.impl.dynamic.CypherPersonHasInterestTag;
import tcc.cypher.impl.dynamic.CypherPersonIsLocatedInPlace;
import tcc.cypher.impl.dynamic.CypherPersonKnowsPerson;
import tcc.cypher.impl.dynamic.CypherPersonLikesComment;
import tcc.cypher.impl.dynamic.CypherPersonLikesPost;
import tcc.cypher.impl.dynamic.CypherPersonStudyAtOrganisation;
import tcc.cypher.impl.dynamic.CypherPersonWorkAtOrganisation;
import tcc.cypher.impl.dynamic.CypherPost;
import tcc.cypher.impl.dynamic.CypherPostHasCreatorPerson;
import tcc.cypher.impl.dynamic.CypherPostHasTagTag;
import tcc.cypher.impl.dynamic.CypherPostIsLocatedInPlace;
import tcc.cypher.impl.statik.CypherOrganisation;
import tcc.cypher.impl.statik.CypherOrganisationIsLocatedInPlace;
import tcc.cypher.impl.statik.CypherPlace;
import tcc.cypher.impl.statik.CypherPlaceIsPartOfPlace;
import tcc.cypher.impl.statik.CypherTag;
import tcc.cypher.impl.statik.CypherTagClass;
import tcc.cypher.impl.statik.CypherTagHasTypeTagclass;
import tcc.cypher.impl.statik.CypherTagclassIsSubclassOfTagclass;

public class CypherFactory {

	public static Cypher create(String csv) {

		switch (csv) {

		// static:
		case "organisation_0_0.csv"                  : return new CypherOrganisation();//OK
		case "place_0_0.csv"                         : return new CypherPlace();//OK
		case "tag_0_0.csv"                           : return new CypherTag();//OK
		case "tagclass_0_0.csv"                      : return new CypherTagClass();//OK
		case "organisation_isLocatedIn_place_0_0.csv": return new CypherOrganisationIsLocatedInPlace();//OK
		case "place_isPartOf_place_0_0.csv"          : return new CypherPlaceIsPartOfPlace();//OK
		case "tag_hasType_tagclass_0_0.csv"          : return new CypherTagHasTypeTagclass();//OK
		case "tagclass_isSubclassOf_tagclass_0_0.csv": return new CypherTagclassIsSubclassOfTagclass();//OK
		
		
		// dynamic:
		case "person_0_0.csv"                     :	return new CypherPerson(); //OK
		case "comment_0_0.csv"                    :	return new CypherComment();//OK
		
		case "forum_0_0.csv"                      :	return new CypherForum();//OK
		case "post_0_0.csv"                       :	return new CypherPost();//OK

		case "person_hasInterest_tag_0_0.csv"     :	return new CypherPersonHasInterestTag();//OK
		case "person_isLocatedIn_place_0_0.csv"   :	return new CypherPersonIsLocatedInPlace(); //OK
		case "person_knows_person_0_0.csv"        :	return new CypherPersonKnowsPerson();//OK
		case "person_likes_comment_0_0.csv"       :	return new CypherPersonLikesComment();//OK
		case "person_likes_post_0_0.csv"          :	return new CypherPersonLikesPost();//OK
		case "person_studyAt_organisation_0_0.csv": return new CypherPersonStudyAtOrganisation();//OK
		case "person_workAt_organisation_0_0.csv" :	return new CypherPersonWorkAtOrganisation();//OK
		
		case "comment_hasCreator_person_0_0.csv"  :	return new CypherCommentHasCreatorPerson();//OK
		case "comment_hasTag_tag_0_0.csv"         :	return new CypherCommentHasTagTag();//OK
		case "comment_isLocatedIn_place_0_0.csv"  :	return new CypherCommentIsLocatedInPlace();//OK -- 2.052.169
		case "comment_replyOf_comment_0_0.csv"    :	return new CypherCommentReplyOfComment();//OK -- 1.040.749
		case "comment_replyOf_post_0_0.csv"       :	return new CypherCommentReplyOfPost();//OK -- 1.011.420
		
		case "forum_containerOf_post_0_0.csv"     :	return new CypherForumContainerOfPost();//OK -- 1.003.605
		case "forum_hasMember_person_0_0.csv"     :	return new CypherForumHasMemberPerson();//OK -- 1.611.869
		case "forum_hasModerator_person_0_0.csv"  :	return new CypherForumHasModeratorPerson();//OK -- 90.492
		case "forum_hasTag_tag_0_0.csv"           :	return new CypherForumHasTagTag();//OK -- 309.766

		case "post_hasCreator_person_0_0.csv"     :	return new CypherPostHasCreatorPerson();//OK -- 1.003.605
		case "post_hasTag_tag_0_0.csv"            :	return new CypherPostHasTagTag();//OK -- 713.258
		case "post_isLocatedIn_place_0_0.csv"     :	return new CypherPostIsLocatedInPlace();//OK -- 1.003.605
			
		default:
			throw new IllegalArgumentException("Unexpected value: " + csv);
		}
	}
}
