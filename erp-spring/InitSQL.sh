#!/bin/bash

# MySQL数据库连接信息
DB_HOST="localhost"
DB_USER="root"
DB_PASS="root"
DB_NAME="erp_db"
TABLE_NAME="models"

# 要检查的项
declare -A ITEMS
ITEMS=(
    [1]="任务中心"
    [2]="库存管理"
    [3]="销售管理"
    [4]="采购管理"
    [5]="人员管理"
    [6]="公司管理"
    [7]="个人管理"
)

for ITEM_ID in "${!ITEMS[@]}"; do
    ITEM_NAME="${ITEMS[$ITEM_ID]}"
    
    # 检查项是否存在
    ITEM_EXISTS=$(mysql -h $DB_HOST -u $DB_USER -p$DB_PASS -D $DB_NAME -se "SELECT COUNT(*) FROM $TABLE_NAME WHERE id=$ITEM_ID;")

    if [ "$ITEM_EXISTS" -eq 0 ]; then
        # 如果项不存在，则插入新的项
        mysql -h $DB_HOST -u $DB_USER -p$DB_PASS -D $DB_NAME -e "INSERT INTO $TABLE_NAME (id, name) VALUES ($ITEM_ID, '$ITEM_NAME');"
        echo "Item with id $ITEM_ID created."
    else
        echo "Item with id $ITEM_ID already exists."
    fi
done