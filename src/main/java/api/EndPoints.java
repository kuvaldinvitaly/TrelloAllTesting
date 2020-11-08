package api;

public final class EndPoints {
    public static final String basePath = "https://api.trello.com/1/";
    public static final String postCreateBoard = "/boards";
    public static final String postCreateList = "/lists";
    public static final String postCreateCard = "/cards";
    public static final String postCreateAttachment = "/cards/{id}/attachments";
    public static final String putUpdateCard = "/cards/{id}";
    public static final String postCreateCheclList = "/cards/{id}/checklists";
    public static final String postCreateCheckitems = "/checklists/{id}/checkItems";
    public static final String putUpdateCheckItem = "/cards/{id}/checkItem/{idCheckItem}";
    public static final String putArchiveList = "/lists/{id}/closed";
    public static final String postCreateComment = "/cards/{id}/actions/comments";


}
