package com.triminds.tlp.integration.dto;

public class ImportResponse {

    private boolean success;
    private int recordsImported;
    private String message;
    private String source;
    private String fileName;
    private String timestamp;

    public ImportResponse() {
    }

    public ImportResponse(
            boolean success,
            int recordsImported,
            String message,
            String source,
            String fileName,
            String timestamp
    ) {
        this.success = success;
        this.recordsImported = recordsImported;
        this.message = message;
        this.source = source;
        this.fileName = fileName;
        this.timestamp = timestamp;
    }

    public static ImportResponse success(int records, String source, String fileName) {
        return new ImportResponse(
                true,
                records,
                "Importação realizada com sucesso",
                source,
                fileName,
                java.time.LocalDateTime.now().toString()
        );
    }

    public static ImportResponse error(String message, String source) {
        return new ImportResponse(
                false,
                0,
                message,
                source,
                null,
                java.time.LocalDateTime.now().toString()
        );
    }

    public boolean isSuccess() {
        return success;
    }

    public int getRecordsImported() {
        return recordsImported;
    }

    public String getMessage() {
        return message;
    }

    public String getSource() {
        return source;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setRecordsImported(int recordsImported) {
        this.recordsImported = recordsImported;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
