import_tree = {
    "pro_id": "任务名称",
    "pro_status": "状态",
    "pro_schedule": "进度",
    "is_first": "是否优先",
    "unit_id": "所属单位id",
    "unit_name": "所属单位名",
    "ana_objects": [
        {
            "object_id": "分析对象id_1",
            "object_name": "分析对象名称",
            "object_status": "状态",
            "object_schedule": "进度",
            "rule_groups": [
                {
                    "rule_group_id": "规则组名称",
                    "rules": [
                        {
                            "rule_id": "规则名称",
                            "content": {
                                "extract_rule": "提取表达式",
                                "swithch_rule": "转换表达式",
                                "replace_rule": "替换表达式",
                                "supplement_rule": "补充表达式"
                            },
                            "file_dirs": [
                                {
                                    "dir_name": "文件夹名",
                                    "is_scanned": "是否被扫描",
                                    "dir_status": "文件夹状态",
                                    "filenums": "文件个数",
                                    "processednums": "处理文件数",
                                    "files": [
                                        {
                                            "filename": "文件名",
                                            "status": "文件状态",
                                            "data_length": "输出数据条数"
                                        }
                                    ]
                                }
                            ]
                        }
                    ]
                }
            ]
        }
    ]
}
