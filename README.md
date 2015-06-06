# csvProcessor
Creating an annotation based CSV file processor

Similar to how we define Entity in hibernate and then use @Column annotations to map the table columns to the attributes of the entity POJO, this csvProcessor also allows to annotate any POJO with @csvField and @csvFieldSet and then the CsvReader takes care of reading the CSV stream into a list of POJOs.
