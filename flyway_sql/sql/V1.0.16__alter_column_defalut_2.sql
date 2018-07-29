ALTER TABLE m_user ALTER COLUMN user_no SET DEFAULT nextval('m_user_sq1');

ALTER TABLE m_item ALTER COLUMN item_no SET DEFAULT nextval('m_item_sq1');

ALTER TABLE m_attr ALTER COLUMN attr_no SET DEFAULT nextval('m_attr_sq1');

ALTER TABLE m_entity ALTER COLUMN entity_no SET DEFAULT nextval('m_entity_sq1');

ALTER TABLE m_service ALTER COLUMN service_no SET DEFAULT nextval('m_service_sq1');

ALTER TABLE m_message ALTER COLUMN message_no SET DEFAULT nextval('m_message_sq1');

ALTER TABLE m_form ALTER COLUMN form_no SET DEFAULT nextval('m_form_sq1');

ALTER TABLE m_screen ALTER COLUMN screen_no SET DEFAULT nextval('m_screen_sq1');

ALTER TABLE m_panel ALTER COLUMN panel_no SET DEFAULT nextval('m_panel_sq1');

ALTER TABLE m_itemtype ALTER COLUMN itemtype_no SET DEFAULT nextval('m_itemtype_sq1');

ALTER TABLE m_component ALTER COLUMN component_no SET DEFAULT nextval('m_component_sq1');
