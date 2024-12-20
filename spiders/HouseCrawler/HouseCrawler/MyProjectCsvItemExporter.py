from HouseCrawler import settings
from scrapy.exporters import CsvItemExporter


class MyProjectCsvItemExporter(CsvItemExporter):

    def __init__(self, *args, **kwargs):
        delimiter = settings.CSV_DELIMITER
        kwargs['delimiter'] = delimiter

        fields_to_export = settings.FIELDS_TO_EXPORT
        if fields_to_export:
            kwargs['fields_to_export'] = fields_to_export

        super(MyProjectCsvItemExporter, self).__init__(*args, **kwargs)
